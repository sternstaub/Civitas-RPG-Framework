# Civitas RPG Framwork

A plugin for paper-based minecraft servers. It provides an API for RPG-related stuff.
It does not do anything in the game by itself, but other plugins can use it. 

The classes herein are thus not supposed to hold world-related content (like xyz positions). Instead, it provides different types of economies (possibility for bank system, gold / item standard, vault support etc.), buildings (possibility to bring RTS-systems in), rpg-talent-systems (linear, perks etc.)

My goal is to create a minecraft-based immersive multiplayer role playing experience through paper plugin ecosystems. for this, i want to use this framework and write subplugins ("Civitas Plugins") that do the "real work". The Civitas Plugins will then attach the classes to each other (for example, handles how guilds can buy buildings).

the features i am thinking of are:

* skill system that allows the players to specialize their craft
* player towns with real time strategy elements, chunk protection, ressource gain and upkeep (like in [TownyPlots](https://dev.bukkit.org/projects/townyplots) extended, but better)


I want to thank the [Lyria server and it's staff](https://lyriaserver.de) for all the inspiration regarding the possibilities of minecraft.


***This is at an early stage.***


# working on initial release (0.1).
 

## Provide different modules

* Economy Module
* Custom Item Module
* Custom Crafting Module
* Productive Building Module
* Skill System Module
* 
