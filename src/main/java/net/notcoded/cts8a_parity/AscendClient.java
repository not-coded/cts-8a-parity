package net.notcoded.cts8a_parity;

import io.github.betterclient.acl.CustomModBuilder;

public class AscendClient {
    public void onAscend() {
        CustomModBuilder.builder()
                .setModName("Bedrock Bridge")
                .setLoadFunction(() -> false)
                .setSaveFunction(empty -> {})
                .setCustomToggle(aBoolean -> {
                    if (CTS8aParity.bridgingAllowed) {
                        CTS8aParity.enabledBridging = aBoolean;
                    }

                    return CTS8aParity.bridgingAllowed;
                })
        .register();
    }
}
