package com.sqisland.android.espresso.hermetic

import dagger.BindsInstance
import dagger.Component
import net.bytebuddy.implementation.bind.annotation.BindingPriority

@Component
interface TestComponent: ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun build(): TestComponent

        @BindsInstance
        fun clock(clock: Clock): Builder
    }
}