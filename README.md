*After exploring the Nether for a bit, you realize that there was something missing about it.*<br>
*You're not too sure what that something is yet, so you decide to construct a research table with the resources you've collected along your journey to lay down your observations.*<br>
*To your surprise, a mysterious voice pierces the air.*

# Nexcavate

A Slimefun Addon about rebuilding the ancient civilization.<br>
Begin the reconstruction by researching and analyzing its traces.<br>
My entry for the 2022 Addon Jam.

*For the Addon Jam, some adjustments have been made to the items for a quicker judging process.*

For a tutorial/walkthrough, see [TUTORIAL.md](https://github.com/qwertyuioplkjhgfd/Nexcavate/blob/main/TUTORIAL.md)

## Commands

`/nexcavate validate`

This command is useful if you built a structure but it does not work.<br>
Run the command while looking at the "centerpiece" of the structure, facing the direction the recipe is displayed (where up in the GUI is forwards for you)<br>
The "centerblock" of a structure is the block you interact with (such as the cartography table for Research Table), or the very bottom back left block of an assembly (you may have to remove a gray stained glass to point towards this block for some assemblies)<br>
Because of this, if you cannot directly look at the centerpiece of an assembly in the correct orientation, you can optionally specify the orientation you want to validate the structure.<br>
The orientation argument is an int as follows:
0. facing -z
1. facing +x
2. facing +z
3. facing -x

Once you run the command, it will show you the first block in the structure that is incorrect.

![](https://raw.githubusercontent.com/qwertyuioplkjhgfd/Nexcavate/main/img/command_1.png)

*Running `/nex validate NE_RESEARCH_TABLE` at this position would yield "Structure is valid!", since I am facing forward relative to the recipe and am looking at the centerpiece (the cartography table)*


![](https://raw.githubusercontent.com/qwertyuioplkjhgfd/Nexcavate/main/img/command_2.png)

*Trying to run `/nex validate NE_RESEARCH_TABLE` would yield false results, since my orientation is turned it will try to validate it as if the research table is pointing that way.*
*Instead, the command to run should be `/ne validate NE_RESEARCH_TABLE 0` (or the correct orientation of the structure based on the table)*

`/nexcavate research`

This command will grant all researches to the sender.