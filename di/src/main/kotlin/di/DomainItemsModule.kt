package di

import com.sottti.android.app.template.usecase.ObserveItems
import com.sottti.android.app.template.usecase.ObserveItemsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainItemsModule {

    @Binds
    @Singleton
    abstract fun bindObserveItems(
        impl: ObserveItemsImpl,
    ): ObserveItems
}
