package com.holinaty.treegenerator

import processing.core.*

class Tree(val pa: TreeGenerator, var size: Int) {

    private var pos = PVector(pa.width * 0.5f, pa.height.toFloat())
    private var dir = PVector(0f, -1f)
    private var root: Branch
    private var branches = ArrayList<Branch>()
    private var leaves = ArrayList<Leaf>()
    private var found = false
    private var treeSpread = pa.random(0.05f, 0.3f)

    init {
        root = Branch(pa, null, pos, dir)

        for (x in 0..size) {
            leaves.add(Leaf(pa))
        }

        branches.add(root)
        var current = root

        while (!found) {
            for (leaf in leaves) {
                val distance = PVector.dist(current.pos, leaf.pos)
                if (distance < pa.max_dist) {
                    found = true
                }
            }

            if (!found) {
                val branch = current.next()
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
                val distance = PVector.dist(leaf.pos, branch.pos)
                if (distance < pa.min_dist) {
                    leaf.reached = true
                    closestBranch = null
                    break
                } else if (distance > pa.max_dist) {
                    // nothing //
                } else if (closestBranch == null || distance < record) {
                    closestBranch = branch
                    record = distance
                }
            }

            if (closestBranch != null) {
                val newDir = PVector.sub(leaf.pos, closestBranch.pos)
                newDir.normalize()
                closestBranch.dir.add(newDir)
                closestBranch.count++
                Branch.countUp()
            }
        }

        for (i in leaves.size - 1 downTo 0) {
            if (leaves[i].reached) {
                leaves.removeAt(i)
            }
        }

        for (i in branches.size - 1 downTo 0) {
            val branch = branches[i]
            if (branches[i].count > 0) {
                branch.dir.div(branch.count.toFloat())
                branches.add(branch.next())
            }
            branch.reset()
        }

        if (leaves.size < size * treeSpread) {
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
        treeSpread = pa.random(0.15f, 0.3f)
        pos = PVector(pa.width * 0.5f, pa.height.toFloat())
        dir = PVector(0f, -1f)
        found = false
        branches.clear()
        leaves.clear()
        size = pa.random(300f, 2500f).toInt()
        root = Branch(pa, null, pos, dir)

        for (x in 0..size) {
            leaves.add(Leaf(pa))
            Leaf.setHeightOffset(pa.random(100f, 400f))
        }

        branches.add(root)
        var current = root

        while (!found) {
            for (leaf in leaves) {
                val distance = PVector.dist(current.pos, leaf.pos)
                if (distance < pa.max_dist) {
                    found = true
                }
            }

            if (!found) {
                val branch = current.next()
                current = branch
                branches.add(current)
            }
        }
    }
}