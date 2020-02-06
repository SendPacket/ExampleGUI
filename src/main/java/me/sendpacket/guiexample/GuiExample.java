package me.sendpacket.guiexample;

import me.sendpacket.easyguilib.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiExample extends JavaPlugin {

    public static gui menu = new gui("gui_example_1","[Example]");
    public static JavaPlugin plugin;

    // Set-up Windows
    public void load_menu()
    {
        gui_window window = new gui_window("Main Window",0,9,menu);
        window.get_items().add(new gui_item_button("item_1", "Item1", "This is an item", 0, Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_button("item_2", "Item2", "This is an item", 1, Material.BLUE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_button("item_3", "Item3", "This is an item", 2, Material.LIME_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_button("item_4", "Item4", "This is an item", 3, Material.ORANGE_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_button("item_5", "Item5", "This is an item", 4, Material.RED_STAINED_GLASS_PANE));
        window.get_items().add(new gui_item_button("item_6", "Item6", "Go to second menu", 6, Material.RED_STAINED_GLASS_PANE));

        gui_window window2 = new gui_window("Second Window",1,9,menu);
        window2.get_items().add(new gui_item_button("item_1", "Item1", "This is an item", 0, Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        window2.get_items().add(new gui_item_button("item_2", "Item2", "This is an item", 1, Material.BLUE_STAINED_GLASS_PANE));
        window2.get_items().add(new gui_item_button("item_3", "Item3", "This is an item", 2, Material.LIME_STAINED_GLASS_PANE));
        window2.get_items().add(new gui_item_button("item_4", "Item4", "This is an item", 3, Material.ORANGE_STAINED_GLASS_PANE));
        window2.get_items().add(new gui_item_button("item_5", "Item5", "This is an item", 4, Material.RED_STAINED_GLASS_PANE));
        window2.get_items().add(new gui_item_returnbutton("item_6", "Item6", "This is a return item", 6,0));

        menu.add_window(window); // Add first window
        menu.add_window(window2); // Add second window

        menu.set_main_window(window); // IMPORTANT
    }

    @Override
    public void onEnable() {
        Bukkit.getServer().getLogger().info("[ExampleGUI] Loaded");

        plugin = this; // Set instance

        gui_values.gui_list.add(menu); // Add menu to global list

        GuiButtons.menu_button_update(); // Start Scheduler

        Bukkit.getServer().getPluginManager().registerEvents(new gui_update(), this); // IMPORTANT [ Update Menu Events ]

        load_menu(); // Load windows
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            if(command.getName().equalsIgnoreCase("example"))
            {
                player.sendMessage("Opening Example GUI");
                gui_utils.open_gui(player, "gui_example_1");
                return false;
            }
        }

        return super.onCommand(sender, command, label, args);
    }
}
