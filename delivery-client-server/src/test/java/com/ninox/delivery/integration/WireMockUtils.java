package com.ninox.delivery.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

public final class WireMockUtils {
    private WireMockUtils() { }

    private static WireMockServer wireMockServer;
    
    private static int SERVER_PORT = 9093;

    public static void initWireMockServer() {
        initWireMockServer(SERVER_PORT);
    }

    public static void initWireMockServer(int port) {
        SERVER_PORT = port > 0 ? port : SERVER_PORT;
        if (wireMockServer == null || !wireMockServer.isRunning()) {
            synchronized (WireMockUtils.class) {
                if (wireMockServer == null || !wireMockServer.isRunning()) {
                    WireMockServer wireMockServer = new WireMockServer(SERVER_PORT);
                    WireMockUtils.wireMockServer = wireMockServer;
                    startWireMockServer();
                }
            }
        }
    }

    public static void startWireMockServer() {
        if (wireMockServer != null && !wireMockServer.isRunning()) {
            wireMockServer.start();
            configureFor(SERVER_PORT);
        }
    }

    public static void stopWireMockServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    public static void removeStub(StubMapping stub) {
        wireMockServer.removeStub(stub);
    }
}
