package cu.wilux.endsurvival;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;

public class GrindstoneEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void open(InventoryClickEvent e) {
        if(e.getView().getType().equals(InventoryType.GRINDSTONE)) {
            System.out.println(e.getRawSlot());
            if(e.getRawSlot()<=2) return;
            if(arrContains(e.getCurrentItem().getType())) {
                System.out.println(e.getSlot());
                if(e.isShiftClick()) {
                    GrindstoneInventory inv = (GrindstoneInventory) e.getInventory();
                    boolean doRecipe = false;
                    ItemStack currentItem = new ItemStack(e.getCurrentItem());
                    if(inv.getStorageContents()[0]==null) {
                        doRecipe = true;
                        inv.setItem(0, currentItem);
                        e.setCurrentItem(null);
                    }
                    else if(inv.getStorageContents()[0].isSimilar(currentItem)) {
                        doRecipe = true;
                        ItemStack grinderItem = inv.getStorageContents()[0];
                        int stackSize = grinderItem.getMaxStackSize();
                        int totalCount = grinderItem.getAmount()+currentItem.getAmount();
                        if(totalCount>=stackSize) {
                            grinderItem.setAmount(stackSize);
                            e.getCurrentItem().setAmount(totalCount-stackSize);
                        }
                        else {
                            grinderItem.setAmount(totalCount);
                            e.setCurrentItem(null);
                        }
                    }
                    if(doRecipe){
                        System.out.println("YES");
                        ItemStack i = new ItemStack(inv.getStorageContents()[0]);
                        if(true) {
                            if(i.getType().equals(Material.END_STONE)) i.setType(Material.SAND);
                            else if(i.getType().equals(Material.CHORUS_FLOWER)) i.setType(Material.BONE_MEAL);
                            else if(i.getType().equals(Material.PURPUR_BLOCK)) i.setType(Material.COARSE_DIRT);
                            else if(i.getType().equals(Material.ENDER_PEARL)) i.setType(Material.CYAN_DYE);
                            else if(i.getType().equals(Material.PRISMARINE_SHARD)) i.setType(Material.REDSTONE);
                        }
                        inv.setItem(2, i);

                        e.setCancelled(true);
                    }
                    else System.out.println("NO");
                }
            }
        }
    }

    private static final Material[] MAT = {Material.END_STONE,Material.CHORUS_FLOWER,Material.PURPUR_BLOCK,Material.ENDER_PEARL,Material.PRISMARINE_SHARD};

    private boolean arrContains(Material b) {
        for (Material element : MAT) {
            if (element == b) {
                return true;
            }
        }
        return false;
    }


}
