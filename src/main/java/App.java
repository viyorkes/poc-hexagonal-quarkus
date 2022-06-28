import com.poc.quakus.network.adapter.NotifyEventWebSocketAdapter;
import com.poc.quakus.network.adapter.input.RouterManageNetworkAdapter;
import com.poc.quakus.network.adapter.output.file.RouterNetworkRestAdapter;
import com.poc.quakus.network.adapter.output.file.kafka.NotifyEventKafkaAdapter;
import com.poc.quakus.network.application.ports.input.RouterNetworkInputPort;
import com.poc.quakus.network.application.ports.output.NotifyEventOutputPort;
import com.poc.quakus.network.application.usecase.RouterNetworkOutputPort;
import com.poc.quakus.network.application.usecase.RouterNetworkUseCase;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;


public class App {

    RouterManageNetworkAdapter inputAdapter;
    RouterNetworkUseCase usecase;
    RouterNetworkOutputPort routerOutputPort;
    NotifyEventOutputPort notifyOutputPort;

    public static void main(String... args) throws IOException, InterruptedException {
        var adapter = "rest";
        if(args.length>0) {
            adapter = args[0];
        }
        new App().setAdapter(adapter);
    }

    void setAdapter(String adapter) throws IOException, InterruptedException {
        switch (adapter){
            case "rest":
                //     routerOutputPort = RouterNetworkH2Adapter.getInstance();
                notifyOutputPort = NotifyEventKafkaAdapter.getInstance();
                usecase = new RouterNetworkInputPort(routerOutputPort, notifyOutputPort);
                inputAdapter= new RouterNetworkRestAdapter(usecase);
                rest();
                NotifyEventWebSocketAdapter.startServer();
                break;
            default:
                cli();
        }
    }

    private void cli() {
        Scanner scanner = new Scanner(System.in);
        inputAdapter.processRequest(scanner);
    }

    private void rest() {
        try {
            System.out.println("REST endpoint listening on port 8080...");
            var httpserver = HttpServer.create(new InetSocketAddress(8080), 0);
            inputAdapter.processRequest(httpserver);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}