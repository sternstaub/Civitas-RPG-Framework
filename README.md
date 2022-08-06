***This section handles the idea and concept behind the Civitas RPG project in general. For this specific project page, refer to the parts after the next section***

The project Civitas RPG aims to bring a minecraft-based multiplayer role playing experience to minecraft. To achieve this, it forms an ecosystem of multiple plugins. 


I want to thank the [Lyria server and it's staff](https://lyriaserver.de) for all the inspiration regarding the possibilities of minecraft. From the many days of playing there, i learned that essential RPG concepts can be realized in a simple, yet effective way. In combination with a well-maintained forum and other platforms, people can build true communities, tell great tales and spend a good time with each other. I have learned that using minecraft as a platform for role playing opens up many possibilities and offers a nice amount of flexibility.

Also, i have written the [TownyPlots Extended](https://www.curseforge.com/minecraft/bukkit-plugins/townyplots) plugin in the past (bukkit times :')). Thus, i will prototype the Civitas Ecosystem on Towny as a starting point. Productive buildings - like they existed in TownyPlots - will definitley be a part of the framework. But also, i intend on introducing guild buildings, so that guilds may buy buildings in different towns to gain influence for example.

Please note that Civitas is, and will be, written for [PaperMC server software](https://papermc.io/). It is not supposed to work on other platforms (tho it aswell might - but i don't support it).

***This is at an early stage.***

# The Civitas Framework plugin

***This section is about this plugin / this source code here.*** 

In other words, it handles the Civitas Framework plugin, which is provides kind of an API for different aspects of role playing. The subplugins can then use the given archetypes to create interactions between the Civitas Modules (read the packages below). To give an example, a guild might function as a city owner, or it might aswell be a bank guild which does not own whole cities, but buys buildings in other players cities to gain some value from it.

However, it must be noted that the classes herein are not supposed to hold world-related content (like xyz positions). Also, don't expect to find many specific implementations in the CivitasFramework. The Framework plugin does not do anything within the game or the world by itself, but other plugins can use it. 
It holds archetypes that can be used by CivitasPlugins, providing an API for any RPG-related stuff and thus allowing the plugins which implement it to be more dynamic and even interact with other plugins that use Civitas. 
Consider it to be a blueprint collection.

## General aims

Generally, this framework will provide different packages, for example:

* Economy Module
* Custom Item Module
* Trading / Market Module
* Custom Crafting Module
* Civilization Module (real-time strategy stuff)
* Skill System Module
* Chat Module
* Lawsuit Module
* NPC Module
* Monster Module
* Encounter Module
* Street / land flagging Module
* Survival Module (disease / hypothermia)

These packages shall be loosely interconnected (Java reflections). That means: a building CAN have a certain type of economy object attached to it. A Monster CAN drop certain items. A guild CAN be settled in a town. I capitalized the "can", because the actual implementation of buildings happens in the Civitas Plugins (kind of child plugins of the framework). The Framework will mainly provide abstract types and handler classes.
This means that the CivitasPlugins which a server owner chooses to use will also determine which parts of the framework shall be used and which shall not.

### Specific considerations
There are some obstacles that should be considered while developing this plugin. Please remember that they only refer

* BuildingFrameworkObjects do not have a world position. They are "roomless". 
* Generally, FrameworkObjects shall only provide methods to interact with each other.
* I would like to add [matrix](https://www.matrix.org)-support for chats
* The Framework plugin will also do the data handling. That means to save and load the data of CivitasPlugins, aswell as checking their integrity.


## Current release stage

Currently working on ***planning stage; pre-release***.
(there was already an attempt of coding, but i chose to change the concept and decentralize it, so it must be redone)

First, i shall create the first version of the framework plugin. then, based on this, i will revive the old [TownyPlots Extended](https://cursefire.com/minecraft/bukkit-plugins/townyplots) to use this Framework. It will apply the buildings from civitas to towny plots.



further notes:
* skill system that allows the players to specialize their craft
* player towns with real time strategy elements, chunk protection, ressource gain and upkeep (like in [TownyPlots](https://dev.bukkit.org/projects/townyplots) extended, but better) 
* different types of economies (possibility for bank system, gold / item standard, vault support etc.), buildings (possibility to bring RTS-systems in), rpg-talent-systems (linear, perks etc.)



