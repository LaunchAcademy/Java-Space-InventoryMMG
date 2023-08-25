import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Spaceship {
  private static final String CREW_FILE = "crewManifest.json";
  private static final String INVENTORY_FILE = "inventory.json";
  private static final String REMOVE_INVENTORY_FILE = "removeInventory.json";

  public static void main(String[] args) throws IOException {
//    byte[] crewManifestContents = Files.readAllBytes(Paths.get(CREW_FILE));
//    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
//    HashMap<String, String> crewHash = objectMapper.readValue(crewManifestContents, HashMap.class);

    StringBuilder output = new StringBuilder("Crew:\n-----\n");

    Map<String, String> crewHash = jsonReader(CREW_FILE);

//    System.out.println("Crew:\n-----");
//    for(String rank : crewHash.keySet()) {
//      String name = crewHash.get(rank);
//      System.out.println("Rank: " + rank + ", Name: " + name);
//    }
//    crewHash.forEach((key, value) -> {
//      System.out.println("Rank: " + key + ", Name: " + value);
//    });

    for(Map.Entry<String, String> crewMember : crewHash.entrySet()) {
//      System.out.println("Rank: " + crewMember.getKey() + ", Name: " + crewMember.getValue());
      output.append("Rank: ").append(crewMember.getKey()).append(", ");
      output.append("Name: ").append(crewMember.getValue()).append("\n");
    }

    System.out.println(output);

    Map<String, Integer> inventoryHash = jsonReader(INVENTORY_FILE);
    Map<String, Integer> removeInventoryHash = jsonReader(REMOVE_INVENTORY_FILE);

// remove items here from hash
    for(String item : removeInventoryHash.keySet()) {
      int removeQuantity = removeInventoryHash.get(item);
      int quantity = inventoryHash.get(item);
      int newQuantity = quantity - removeQuantity;

      if(newQuantity < 1) {
//        System.out.println(item);
        inventoryHash.remove(item);
      } else {
        inventoryHash.put(item, newQuantity);
      }
    }

    System.out.println("Inventory:\n-----");
    for(String item : inventoryHash.keySet()) {
      int quantity = inventoryHash.get(item);
      System.out.println("Item: " + item + ", Quantity: " + quantity);
    }

//    System.out.println(output);
  }

  private static Map jsonReader(String filePath) throws IOException {
    byte[] crewManifestContents = Files.readAllBytes(Paths.get(filePath));
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    return objectMapper.readValue(crewManifestContents, HashMap.class);
  }
}
