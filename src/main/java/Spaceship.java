import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class Spaceship {
  private static final String CREW_FILE = "crewManifest.json";
  private static final String INVENTORY_FILE = "inventory.json";
  private static final String REMOVE_INVENTORY_FILE = "removeinventory.json";
  private static final String CAPTAIN = "Captain";

  private static Map jsonReader(String filepath) throws IOException {
    byte[] mapData = Files.readAllBytes(Paths.get(filepath));
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    return objectMapper.readValue(mapData, HashMap.class);
  }

  public static void main(String[] args) throws IOException {
    Map<String, String> crewMap = jsonReader(CREW_FILE);
    System.out.println("Crew: \n----------");
    crewMap.forEach((key, value) -> {
      System.out.println("Rank: " + key + ",  Name: " + value);
    });

    Map<String, Integer> inventoryMap = jsonReader(INVENTORY_FILE);
    System.out.println("\n\n INVENTORY \n---------");
    inventoryMap.forEach((key, value)->{
      System.out.println("Item: "+ key + ": " + value);
    });

  }
}
