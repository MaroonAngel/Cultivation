package net.maroonangel.cultivation.gen;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class Features {

    private static final Feature<DefaultFeatureConfig> POD_SHELL = new PodShellFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredFeature<?, ?> POD_SHELL_CONFIGURED = POD_SHELL.configure(FeatureConfig.DEFAULT)
            .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(20)));

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier("cultivation", "pod_shell"), POD_SHELL);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("cultivation", "pod_shell"), POD_SHELL_CONFIGURED);
    }
}
