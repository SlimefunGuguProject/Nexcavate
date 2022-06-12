package me.char321.nexcavate.structure;

import me.char321.nexcavate.structure.piece.MaterialStructurePiece;
import me.char321.nexcavate.structure.piece.StructurePiece;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 * a 3d structure of blocks
 */
public class Structure {
    private final StructurePiece[][][] structure;
    private final int[] center;
    /**
     * the sidelength of the cube
     */
    public final int size;

    public Structure(StructurePiece[][][] structure, int[] center) {
        this.structure = structure;
        this.center = center;
        this.size = structure.length;
    }

    public Structure(StructurePiece[][][] structure) {
        this(structure, new int[]{0, 0, 0});
    }

    public StructurePiece[][][] getPieces() {
        return structure;
    }

    public int[] getCenter() {
        return center;
    }

    public StructurePiece getCenterPiece() {
        return structure[center[0]][center[1]][center[2]];
    }

    /**
     * checks if this structure is valid at this block
     *
     * @param center the location of the block which should be the centerpiece
     * @return whether this structure is valid with this block as the centerpiece
     */
    public boolean validate(Location center) {
        //TODO: check command
        return orientation(center) != -1;
    }

    /**
     * same as validate but returns which orientation the thing is in
     *
     * @param center the location of the block which should be the centerpiece
     * @return the orientation of the structure<br>
     * <table style="margin:3px">
     *   <tr>
     *     <td> orientation </td> <td> facing</td>
     *   </tr>
     *   <tr>
     *     <td> 0 </td> <td> -z </td>
     *   </tr>
     *   <tr>
     *     <td> 1 </td> <td> +x </td>
     *   </tr>
     *   <tr>
     *     <td> 2 </td> <td> +z </td>
     *   </tr>
     *   <tr>
     *     <td> 3 </td> <td> -x </td>
     *   </tr>
     * </table>
     */
    public int orientation(Location center) {
        for (int orientation = 0; orientation < 4; orientation++) {
            if (validateOrientation(center, orientation)) {
                return orientation;
            }
        }
        return -1;
    }

    public boolean validateOrientation(Location center, int orientation) {
        for (int layer = 0; layer < structure.length; layer++) {
            if (!validateLayer(center, layer, orientation)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateLayer(Location centerPos, int layerIndex, int orientation) {
        StructurePiece[][] layer = structure[layerIndex];
        for (int z = 0; z < layer.length; z++) {
            for (int x = 0; x < layer[z].length; x++) {
                int dy = layerIndex - center[0]; //if center is origin
                int dz = z - center[1];
                int dx = x - center[2];
                int temp;
                switch (orientation) {
                    case 0:
                        break;
                    case 1:
                        temp = dx;
                        dx = -dz;
                        dz = temp;
                        break;
                    case 2:
                        dx = -dx;
                        dz = -dz;
                        break;
                    case 3:
                        temp = dx;
                        dx = dz;
                        dz = -temp;
                        break;
                }
                Location location = centerPos.clone().add(dx, dy, dz);
                if (!layer[z][x].isValid(location.getBlock())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * creates a structure using literal pieces
     *
     * @param materials the materials of the structure, must be a cube and length > 0
     * @param center 3 ints that define the indices of which piece is the center piece, or the piece that is passed in validate()
     * @return the structure
     */
    public static Structure literal(Material[][][] materials, int[] center) {
        StructurePiece[][][] structure = new StructurePiece[materials.length][materials[0].length][materials[0][0].length];
        for (int i = 0; i < materials.length; i++) {
            for (int ii = 0; ii < materials[i].length; ii++) {
                for (int iii = 0; iii < materials[i][ii].length; iii++) {
                    structure[i][ii][iii] = new MaterialStructurePiece(materials[i][ii][iii]);
                }
            }
        }
        return new Structure(structure, center);
    }
}
