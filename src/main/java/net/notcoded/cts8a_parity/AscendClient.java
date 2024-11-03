package net.notcoded.cts8a_parity;

import io.github.betterclient.acl.CustomModBuilder;

import java.io.File;
import java.io.IOException;

public class AscendClient {
    public void onAscend() {
        CustomModBuilder.builder()
                .setModName("Bedrock Bridge")
                .setLoadFunction(() -> new File("BedrockBridgeState.txt").exists())
                .setSaveFunction(state -> {
                    File f = new File("BedrockBridgeState.txt");
                    try {
                        boolean unused = state ? f.createNewFile() : f.delete();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .setCustomToggle(aBoolean -> {
                    if (CTS8aParity.bridgingAllowed) {
                        CTS8aParity.enabledBridging = aBoolean;
                    }

                    return CTS8aParity.bridgingAllowed;
                })
        .register();
    }
}
