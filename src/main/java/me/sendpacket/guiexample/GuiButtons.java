package me.sendpacket.guiexample;

import me.sendpacket.easyguilib.*;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GuiButtons {

    // Main Window Buttons
    public static void update_buttons_window1(Player p,gui_item item)
    {
        switch (item.get_id()) {
            case "item_1":
                p.sendMessage("Pressed item 1 in first window");
                break;
            case "item_2":
                p.sendMessage("Pressed item 2 in first window");
                break;
            case "item_3":
                p.sendMessage("Pressed item 3 in first window");
                break;
            case "item_4":
                p.sendMessage("Pressed item 4 in first window");
                break;
            case "item_5":
                p.sendMessage("Pressed item 5 in first window");
                break;
            case "item_6":
                p.sendMessage("Pressed item 6 in first window");
                gui_utils.jump_to_window(p, GuiExample.menu, 1); // Jump to second menu
                break;
        }
    }

    // Second Window Buttons
    public static void update_buttons_window2(Player p,gui_item item)
    {
        switch (item.get_id()) {
            case "item_1":
                p.sendMessage("Pressed item 1 in second window");
                break;
            case "item_2":
                p.sendMessage("Pressed item 2 in second window");
                break;
            case "item_3":
                p.sendMessage("Pressed item 3 in second window");
                break;
            case "item_4":
                p.sendMessage("Pressed item 4 in second window");
                break;
            case "item_5":
                p.sendMessage("Pressed item 5 in second window");
                break;
        }
    }

    public static void menu_button_update()
    {
        Bukkit.getScheduler().runTaskTimer(GuiExample.plugin, () -> {
            // GUI 1
            try {
                // List Windows
                for (gui_window w : GuiExample.menu.get_windows()) {
                    // List Players
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        // List Items in window
                        for (gui_item item : w.get_items()) {
                            // Item is button
                            if (ArrayUtils.contains(gui_values.gui_button_list, item.get_type())) {
                                // Was pressed and action is > 0 [ Action 1 : LButton | Action 2 : RButton | Action 3 : MButton ]
                                if (item.pressed_value(p) > 0) {
                                    // Update return buttons
                                    if (item.get_type().equals(gui_item_type.return_button)) {
                                        gui_utils.jump_to_window(p, GuiExample.menu, ((gui_item_returnbutton)item).get_window_id_to_return());
                                    } else {
                                        // Update normal buttons
                                        switch (w.get_id()) { // Gui Window ID
                                            case 0: // Main Window
                                                update_buttons_window1(p, item);
                                                break;
                                            case 1: // Second Window
                                                update_buttons_window2(p, item);
                                                break;
                                        }
                                    }
                                    item.not_pressed(p); // IMPORTANT
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){};
        },0L,1L);
    }
}
