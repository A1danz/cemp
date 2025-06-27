import com.cemp.data.network.HttpEngineFactory
import com.cemp.data.network.createHttpClient
import com.cemp.data.repository.MatchesRepositoryImpl
import com.cemp.data.repository.TeamsRepositoryImpl
import com.cemp.domain.model.base.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test

// not unit-test, only for test network requests without build full project
class HttpClientTest {

    private val client = createHttpClient(HttpEngineFactory().createEngine(), Json {
        ignoreUnknownKeys = true
        isLenient = true

    })
    private val matchesRepo = MatchesRepositoryImpl(client, Dispatchers.IO)
    private val teamsRepo = TeamsRepositoryImpl(client, Dispatchers.IO)

    @Test
    fun testMatchesRequest() {
        runTest {
            val matches = matchesRepo.getMatches()

            if (matches is NetworkResponse.Success) {
                matches.data.forEach { println(it) }
            } else if (matches is NetworkResponse.Error) {
                println(matches.err)
            }
        }

    }

    @Test
    fun testMatchesWithFilterRequest() {
        runTest {
            val matches = matchesRepo.getMatches(3212)

            if (matches is NetworkResponse.Success) {
                matches.data.forEach { println(it) }
            }
        }
    }

    @Test
    fun testTeamsRequest() {
        runTest {
            val teams = teamsRepo.getTeams()

            if (teams is NetworkResponse.Success) {
                teams.data.forEach { println(it) }
            }
        }
    }

    @Test
    fun testTeamsWithFilterRequest() {
        runTest {
            val teams = teamsRepo.getTeam(3212)

            if (teams is NetworkResponse.Success) {
                println(teams.data)
            }
        }
    }

}