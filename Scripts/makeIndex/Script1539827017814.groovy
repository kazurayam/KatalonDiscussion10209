import com.kazurayam.materials.MaterialRepository

import internal.GlobalVariable as GlobalVariable

/*
 * makeIndex
 * 
 *  compiles a HTML at ./Materials/index.html
 */

// prepare instance of MaterialRepository
MaterialRepository mr = (MaterialRepository)GlobalVariable.MATERIAL_REPOSITORY
assert mr != null

// make ./Material/index.html file
mr.makeIndex()