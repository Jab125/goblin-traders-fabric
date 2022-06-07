package net.hat.gt.init;

import net.hat.gt.entities.ai.GoblinTraderSpecificSensor;
import net.hat.gt.mixin.SensorTypeAccessor;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.entity.passive.GoatBrain;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Supplier;

public class ModSensorTypes {
    public static final SensorType<GoblinTraderSpecificSensor> GOAT_TEMPTATIONS = register("goblintraders:goblin_trader_sensor", GoblinTraderSpecificSensor::new);


    public static void registerSensorTypes() {}
    static <U extends Sensor<?>> SensorType<U> register(String id, Supplier<U> factory) {
        return SensorTypeAccessor.callRegister(id, factory);
    }
}
