package fantasynetwork.fantasykitpvp.warps

import fantasynetwork.fantasykitpvp.warps.list.Spawn

class WarpManager{
    val warps: List<Warp> = listOf(Spawn())

    fun getWarp(name:String): Warp? = warps.filter { it.getName() == name }.first()

    fun loadAllWarps(){
        warps.toMutableList().add(Spawn())
    }


}