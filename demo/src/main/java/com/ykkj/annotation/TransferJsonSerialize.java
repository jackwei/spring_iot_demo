package com.ykkj.annotation;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.ykkj.enums.TransferStrategyEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * JsonSerializer:是 Jackson 库中的一个核心抽象类，用于自定义对象的序列化逻辑。
 * 它允许开发者实现自己的序列化规则，从而控制对象在被序列化为 JSON 数据时的具体表现形式。
 * 简单来说，JsonSerializer 是一个接口，通过实现这个接口，可以定义如何将 Java 对象转换为 JSON 格式。
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TransferJsonSerialize extends JsonSerializer<Object> implements ContextualSerializer {

    private boolean overrideSelf;

    private String targetFieldName;

    private TransferStrategyEnum transferStrategyEnum;

    /**
     * 自定义序列化逻辑
     *
     * @param object             这是需要序列化的对象。是你自定义的对象类型，它可能是一个简单的类或者是一个复杂的嵌套对象。(这里代表使用了该注解的字段实体对象)
     * @param jsonGenerator      这是Jackson提供的用于生成JSON内容的生成器。通过它，你可以手动构建JSON字段和值。
     * @param serializerProvider 提供了序列化时需要用到的上下文信息，比如其他序列化器，配置选项等。
     */
    @Override
    public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        //执行转换逻辑，获取转换值
        Object transfer = TransferContext.transfer(transferStrategyEnum, object);
        if (transfer == null) {
            log.error("transfer value is null");
            return;
        }

        if (this.overrideSelf) {
            //填充当前字段
            jsonGenerator.writeObject(transfer);
            return;
        }
        //填充源字段值
        jsonGenerator.writeObject(object);

        //获取映射字段对象
        Field field = ReflectUtil.getField(jsonGenerator.getCurrentValue().getClass(), targetFieldName);
        //通过反射将转换至映射到对应字段中
        ReflectUtil.setFieldValue(jsonGenerator.getCurrentValue(), field, transfer);
    }

    /**
     * 该方法用于确定是否需要一个不同的（或不同配置的）序列化器来序列化指定属性的值。
     * 请注意，通常调用该方法的实例是共享的，因此该方法<b>不应</b>修改此实例，而应构造并返回一个新实例。
     * 只有当此实例已经适合使用时，才应原样返回此实例。
     *
     * @param prov     序列化器提供者，用于访问配置和其他序列化器
     * @param property 代表属性的方法或字段（用于访问要序列化的值）。
     *                 通常应该是可用的；但在某些情况下，调用者可能无法提供它，并传递 null
     *                 （在这种情况下，通常实现会将 'this' 序列化器按原样传递）
     * @return 用于序列化指定属性值的序列化器；
     * 可以是此实例或新实例。
     * @throws JsonMappingException
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Transfer annotation = property.getAnnotation(Transfer.class);

        if (annotation != null) {
            /**
             * Jackson 在序列化过程中，会为同一个类型的属性复用相同的 JsonSerializer 实例。如果多个线程同时序列化相同类型的属性，它们可能会共享同一个序列化器实例。
             *
             * 这意味着，即使这些字段是实例变量，而不是静态变量（线程共享变量），如果多个线程同时使用同一个 TransferJsonSerialize 实例，
             * 它们将会同时修改 overrideSelf、targetFieldName 和 transferStrategyEnum 的值，从而引发竞态条件。
             */
            //初始化序列化器上下文。将自定义注解Transfer中的参数初始化到序列化器的字段中，以供执行自定义序列化逻辑时使用
            //每次都返回一个新的序列化器，避免线程安全问题
            return new TransferJsonSerialize(annotation.overrideSelf(), annotation.targetFieldName(), annotation.transferStrategy());
        } else {
            //如果需要序列化的字段上没有该注解，则返回一个新的默认序列化器,该序列化器会替代当前序列化器对实体进行序列化
            return prov.findValueSerializer(property.getType(), property);
        }
    }
}
