import com.sottti.android.app.template.data.network.API_BASE_URL
import com.sottti.android.app.template.data.network.createBaseHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkDataModule {

    @Provides
    @Singleton
    fun provideBaseHttpClient(): HttpClient = createBaseHttpClient()

    @Provides
    @Singleton
    fun provideApiHttpClient(baseClient: HttpClient): HttpClient =
        baseClient.config {
            defaultRequest {
                url.takeFrom(API_BASE_URL)
                contentType(ContentType.Application.Json)
            }
        }
}
