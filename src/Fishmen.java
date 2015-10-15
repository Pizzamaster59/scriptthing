import org.tbot.client.Player;
import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.methods.*;
import org.tbot.methods.tabs.Inventory;
import org.tbot.methods.walking.Path;
import org.tbot.methods.walking.Walking;
import org.tbot.util.Condition;
import org.tbot.wrappers.Item;
import org.tbot.wrappers.NPC;
import org.tbot.wrappers.Tile;

import static org.tbot.methods.Skills.getCurrentLevel;

@Manifest(authors = "me", name = "test1", category = ScriptCategory.MINIGAMES)
/**
 * Created by Leonid on 10/13/2015.
 */
public class Fishmen extends AbstractScript {
    private boolean isFishing() {
        for (int i = 0; i < 15; i++) {
            if (Players.getLocal().getAnimation() != -1) {
                return true;
            }
            Time.sleep(150);
        }
        return false;
    }

    private boolean FishThing2() {
        return Inventory.contains("Fly fishing rod") && !Inventory.isFull();
    }

    private boolean FishThing() {
        return Inventory.contains("Small fishing net") && !Inventory.isFull();
    }

    private boolean drop() {
        return Inventory.isFull();
    }

    @Override
    public int loop() {
        NPC shrimp = Npcs.getNearest("Fishing spot");
        NPC shop = Npcs.getNearest("Gerrant");
        if (Skills.getCurrentLevel(Skills.Skill.FISHING) >= 20 && (Skills.getCurrentLevel(Skills.Skill.FISHING) <= 39
                && Inventory.contains(309) && Inventory.contains(314))) {
            final Path fish2 = Walking.findPath(new Tile(3106, 3433));
            if (Walking.isRunEnabled() && Walking.getRunEnergy() > 50) {
                Walking.setRun(true);
            }
            if (fish2 != null) {
                fish2.traverse();
                if (FishThing2()) {
                    if (shrimp != null)
                        if (shrimp.isOnScreen()) {
                            if (Players.getLocal().getAnimation() == -1) {
                                if (!isFishing())
                                    shrimp.interact("Lure");
                            }
                        } else {
                            if (!shrimp.isOnScreen()) {
                                if (fish2 != null) {
                                    fish2.traverse();
                                }
                            }
                        }
                    else {
                        if (!shrimp.isOnScreen()) {
                            if (fish2 != null) {
                                fish2.traverse();
                            }
                        }
                    }
                    return 160;
                }else if (drop()){
                Inventory.dropAllExcept(309,314,995);}
            }
                } else {
                    if (FishThing()) {
                        if (shrimp != null)
                            if (shrimp.isOnScreen()) {
                                if (Players.getLocal().getAnimation() == -1) {
                                    if (!isFishing())
                                        shrimp.interact("Net");
                                }
                            } else {
                                if (!shrimp.isOnScreen()) {
                                    Path pathToShrimp = Walking.findPath(new Tile(3240, 3143, 0));
                                    if (pathToShrimp != null) {
                                        pathToShrimp.traverse();
                                    }
                                }
                            }
                        else {
                            Path pathToShrimp = Walking.findPath(new Tile(3240, 3143, 0));
                            if (pathToShrimp != null) {
                                pathToShrimp.traverse();
                            }
                        }
                        return 150;
                    } else if (drop()) {
                        Inventory.dropAllExcept(303, 995, 309, 314);
                        return 200;
                    }
                }
                return 120;
            }
        }
