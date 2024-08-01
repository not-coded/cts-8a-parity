# CTS 8a Parity

Provides some useful Combat Test 8(a) features (that were removed in CTS 8b or CTS 8c) for Combat Test 8c.

> [!IMPORTANT]
> Requires [Fabric API](https://github.com/not-coded/fabric/releases)!
> 
> Due to the Networking Changes only being added in **0.26.0**, rizecookey's Fabric API **will not work** due to being a version too **outdated (0.25.0)**. **Please use the link above.**

### Features
- 100% Accurate Bedrock Bridging

![bedrock bridging showcase](https://github.com/not-coded/cts-8a-parity/raw/main/assets/bedrock-bridging.gif)


### For Server Owners
Starting from version **1.0.2**, you can send a packet to **disable/enable** the bedrock bridging functionality.

> [!NOTE]
> Bedrock Bridging is **enabled by default**, you do not need to send a packet to enable it, only for disabling it.

```java
ResourceLocation CONVENTIONAL_BRIDGING_UPDATE_ID = new ResourceLocation("c", "update_status"); // VERY IMPORTANT!

if (ServerPlayNetworking.canSend(player, CONVENTIONAL_BRIDGING_UPDATE_ID)) {
    FriendlyByteBuf buf = PacketByteBufs.create();
    buf.writeBoolean(true); // Set this to true/false depending on if you want to enable or disable it.
    ServerPlayNetworking.send(player, CONVENTIONAL_BRIDGING_UPDATE_ID, buf);
}
```

View https://github.com/not-coded/cts-8a-parity/tree/main/src/test/java/net/notcoded/packettest/PacketTest.java for a code example.

### Build Requirements

- Java 21
- Gradle 8.7

### Build

- Clone the repository
    - `git clone https://github.com/not-coded/cts-8a-parity`
- Run `./gradlew build`
