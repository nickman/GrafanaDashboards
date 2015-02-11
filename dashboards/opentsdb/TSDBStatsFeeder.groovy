URL url = new URL("http://localhost:4242/stats");
Socket sock = new Socket("localhost", 4242);
int metricCount = 0;
int bufferSize = 1024;
long loops = 0;
while(true) {
    int x = 0;
    b = new StringBuilder(bufferSize);
    url.eachLine() {
        b.append("put $it\n");
        x++;
    }
    sock << b.toString().getBytes();
    if(x > metricCount) metricCount = x;
    if(b.length() > bufferSize) bufferSize = b.length();
    if(loops%100==0) {
        println "Metrics: $metricCount, BufferSize: $bufferSize";
    }
    loops++;
    Thread.sleep(5000);
}
