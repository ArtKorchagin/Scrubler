import com.artkorchagin.scrubler.common.data.network.OpensubtitlesApi
import com.artkorchagin.scrubler.common.data.network.createHttpClient
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient() }
    single { OpensubtitlesApi(get()) }
}