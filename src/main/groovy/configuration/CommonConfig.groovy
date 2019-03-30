package configuration

import java.nio.file.Path
import java.nio.file.Paths
import groovyx.net.http.RESTClient

/**
 * Created by Kateryna on 04.11.2017.
 */
class CommonConfig 
{
    static user = System.getProperty('user')
    static password = System.getProperty('password')
    static String projKey
    static String planKey
    static String projName
    static String planName
    static String bambooHome = "${System.env.BAMBOO_HOME}"
    static String bambooVersion

    static Path getBuildDir()
    {
        return Paths.get(bambooHome, "xml-data", "build-dir", "${projKey}-${planKey}-JOB1")
    }

    static {
        def client = new RESTClient("${System.env.BAMBOO_URL}")
        //client.auth.basic "admin", "admin"
        client.headers.'Authorization' = "Basic ${"admin:admin".bytes.encodeBase64().toString()}"
        def response = client.get(path:"/bamboo/rest/api/latest/info")
        bambooVersion = response.data.version
        println("Bamboo version is " + bambooVersion)
    }
}