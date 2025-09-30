package com.ykkj.test;

public class BatteryCalculatorTest {
    // 最大电压（满电）
    private static final int MAX_VOLTAGE = 4000;
    // 最小电压（没电）
    private static final int MIN_VOLTAGE = 3000;

    /**
     * 方法1：考虑最低电压的线性计算
     * 当电压低于最低阈值时视为0%，高于最高阈值视为100%
     */
    public static int linearWithThreshold(int currentVoltage) {
        if (currentVoltage <= MIN_VOLTAGE) {
            return 0;
        }
        if (currentVoltage >= MAX_VOLTAGE) {
            return 100;
        }
        // 计算有效电压范围的比例
        return (int) Math.round(
                (double)(currentVoltage - MIN_VOLTAGE) /
                        (MAX_VOLTAGE - MIN_VOLTAGE) * 100
        );
    }

    /**
     * 方法2：分段线性计算（更符合电池实际放电曲线）
     * 不同电压区间采用不同的转换比例
     */
    public static int segmentedCalculation(int currentVoltage) {
        if (currentVoltage <= MIN_VOLTAGE) return 0;
        if (currentVoltage >= MAX_VOLTAGE) return 100;

        // 分段定义：电压越高，相同电压变化对应更高的百分比变化
        if (currentVoltage < 3300) {
            // 3000-3300V 对应 0-20%
            return (int) Math.round((currentVoltage - 3000) / 300.0 * 20);
        } else if (currentVoltage < 3600) {
            // 3300-3600V 对应 20-50%
            return 20 + (int) Math.round((currentVoltage - 3300) / 300.0 * 30);
        } else if (currentVoltage < 3800) {
            // 3600-3800V 对应 50-80%
            return 50 + (int) Math.round((currentVoltage - 3600) / 200.0 * 30);
        } else {
            // 3800-4000V 对应 80-100%
            return 80 + (int) Math.round((currentVoltage - 3800) / 200.0 * 20);
        }
    }

    /**
     * 方法3：非线性计算（使用平方根函数模拟电池特性）
     * 电压越高，百分比增长越快（符合某些类型电池的特性）
     */
    public static int nonLinearCalculation(int currentVoltage) {
        if (currentVoltage <= MIN_VOLTAGE) return 0;
        if (currentVoltage >= MAX_VOLTAGE) return 100;

        // 标准化到0-1范围
        double normalized = (double)(currentVoltage - MIN_VOLTAGE) / (MAX_VOLTAGE - MIN_VOLTAGE);
        // 应用非线性转换（平方根函数）
        double nonLinearValue = Math.sqrt(normalized);
        return (int) Math.round(nonLinearValue * 100);
    }

    public static void main(String[] args) {
        // 测试不同电压下的计算结果
        int[] testVoltages = {2900, 3000, 3200, 3400, 3600, 3800, 4000, 4100};

        System.out.println("电压\t方法1(阈值线性)\t方法2(分段)\t方法3(非线性)");
        for (int voltage : testVoltages) {
            System.out.printf("%d\t%d%%\t\t%d%%\t\t%d%%%n",
                    voltage,
                    linearWithThreshold(voltage),
                    segmentedCalculation(voltage),
                    nonLinearCalculation(voltage)
            );
        }
    }
}
