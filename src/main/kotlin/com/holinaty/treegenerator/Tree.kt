package com.holinaty.treegenerator

import processing.core.*

class Tree(val pa: SpaceColonizedTree, var size: Int) {


    var pos = PVector(pa.width*0.5f, pa.height.toFloat())
    var dir = PVector(0f,-1f)
    var root: Branch
    var branches = ArrayList<Branch>()
    var leaves = ArrayList<Leaf>()
    var found = false
    var tree_spread = pa.random(0.05f,0.3f)
    var col = 255

    init {
        root = Branch(pa, null, pos, dir)

        for (x in 0..size) {
            leaves.add(Leaf(pa))
        }

        branches.add(root)
        var current = root

        while (!found) {
            for (leaf in leaves) {
                var distance = PVector.dist(current.pos, leaf.pos)
                if (distance < pa.max_dist) {
                    found = true
                }
            }

            if (!found) {
                var branch = current.next()
                current = branch
                branches.add(current)
            }
        }
    }

    fun grow() {
        for (leaf in leaves) {
            var closestBranch: Branch? = null
            var record = 100000f
            for (branch in branches) {
                var distance = PVector.dist(leaf.pos, branch.pos)
                if (distance < pa.min_dist) {
                    leaf.reached = true
                    closestBranch = null
                    break
                } else if (distance > pa.max_dist) {
                    // nothing //
                } else if (closestBranch == null || distance < record ) {
                    closestBranch = branch
                    record = distance
                }
            }

            if (closestBranch != null) {
                var newDir = PVector.sub(leaf.pos, closestBranch.pos)
                newDir.normalize()
                closestBranch.dir.add(newDir)
                closestBranch.count++
                Branch.countUp()
            }
        }

        for (i in leaves.size-1 downTo 0) {
            if (leaves[i].reached) {
                leaves.removeAt(i)
            }
        }

        for (i in branches.size-1 downTo 0) {
            var branch = branches[i]
            if (branches[i].count > 0) {
                branch.dir.div(branch.count.toFloat())
//                var newpos = PVector.add(branch.pos, branch.dir)
//                var newBranch = Branch(pa,branch,newpos, branch.dir.copy())
                branches.add(branch.next())
            }
            branch.reset()
        }

        //
        if (leaves.size < size*tree_spread) {
            leaves.clear()
        }
    }

    fun show(showleafs: Boolean) {

        if (showleafs) {
            pa.fill(255f, 255f, 0f)
            for (leaf in leaves) {
                leaf.show()
            }
        }

        for (branch in branches) {
            branch.show()
        }
    }


    // randomly creates a new tree
    // aka a crude re-initialization
    fun rollNewTree() {
        tree_spread = pa.random(0.15f,0.3f)
        pos = PVector(pa.width*0.5f, pa.height.toFloat())
        dir = PVector(0f,-1f)
        found = false
        branches.clear()
        leaves.clear()
        size = pa.random(300f,2500f).toInt()
        root = Branch(pa, null, pos, dir)

        for (x in 0..size) {
            leaves.add(Leaf(pa))
            Leaf.setHeightOffset(pa.random(100f, 400f))
        }

        branches.add(root)
        var current = root

        while (!found) {
            for (leaf in leaves) {
                var distance = PVector.dist(current.pos, leaf.pos)
                if (distance < pa.max_dist) {
                    found = true
                }
            }

            if (!found) {
                var branch = current.next()
                current = branch
                branches.add(current)
            }
        }
    }
}