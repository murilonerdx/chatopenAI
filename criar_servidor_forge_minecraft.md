Para criar um servidor Minecraft inteiramente em Java, você pode usar a biblioteca de servidor oficial do Minecraft, que fornece uma interface de programação de aplicativos (API) para criar e gerenciar um servidor de jogo Minecraft. Aqui está um exemplo de código que demonstra como fazer isso:

```java
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "mymod", name = "My Mod", version = "1.0", acceptableRemoteVersions = "*")
public class MyMod {
  private static final Logger LOGGER = LogManager.getLogger();

  @EventHandler
  public void init(final FMLInitializationEvent event) {
    MinecraftForge.EVENT_BUS.register(this);
    NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
  }

  @EventHandler
  public void serverLoad(final FMLServerStartingEvent event) {
    final MinecraftServer server = event.getServer();
    // Add commands here
  }

  @SubscribeEvent
  public void onWorldLoad(final WorldEvent.Load event) {
    if (!event.getWorld().isRemote) {
      final WorldServer worldServer = (WorldServer) event.getWorld();
      final WorldInfo worldInfo = worldServer.getWorldInfo();
      // Modify world settings here
      worldInfo.setGameType(GameType.SURVIVAL);
      worldInfo.setHardcore(false);
      worldInfo.setDifficulty(Difficulty.NORMAL);
      worldInfo.setDifficultyLocked(false);
      worldInfo.setAllowCommands(true);
      worldInfo.setRaining(false);
      worldInfo.setThundering(false);
      worldInfo.setThunderTime(0);
      worldInfo.setRainTime(0);
      worldInfo.setClearWeatherTime(0);
      worldInfo.setWorldTime(6000);
      worldInfo.setSpawnX(0);
      worldInfo.setSpawnY(64);
      worldInfo.setSpawnZ(0);
      worldInfo.setBorderCenterX(0);
}

public void onWorldLoad(final WorldEvent.Load event) {
    if (!event.getWorld().isRemote) {
      final WorldServer worldServer = (WorldServer) event.getWorld();
      final WorldInfo worldInfo = worldServer.getWorldInfo();
      // Modify world settings here
      worldInfo.setGameType(GameType.SURVIVAL);
      worldInfo.setHardcore(false);
      worldInfo.setDifficulty(Difficulty.NORMAL);
      worldInfo.setDifficultyLocked(false);
      worldInfo.setAllowCommands(true);
      worldInfo.setRaining(false);
      worldInfo.setThundering(false);
      worldInfo.setThunderTime(0);
      worldInfo.setRainTime(0);
      worldInfo.setClearWeatherTime(0);
      worldInfo.setWorldTime(6000);
      worldInfo.setSpawnX(0);
      worldInfo.setSpawnY(64);
      worldInfo.setSpawnZ(0);
      worldInfo.setBorderCenterX(0);
      worldInfo.setBorderCenterZ(0);
      worldInfo.setBorderSize(6000000.0D);
      worldInfo.setBorderSizeLerpTime(0);
      worldInfo.setBorderSizeLerpTarget(6000000.0D);
      worldInfo.setBorderSafeZone(5.0D);
      worldInfo.setBorderDamagePerBlock(0.2D);
      worldInfo.setBorderWarningBlocks(5);
      worldInfo.setBorderWarningTime(15);
    }
  }
}
```

Este código cria um mod de Forge para o Minecraft que se inscreve no evento de carregamento de mundo e modifica as configurações do mundo quando o evento é disparado. Ele define o tipo de jogo para sobrevivência, desativa o modo hardcore, define a dificuldade para normal, permite comandos, desativa a chuva e o trovão, define a posição do spawn, define as configurações da borda do mundo e muito mais.
