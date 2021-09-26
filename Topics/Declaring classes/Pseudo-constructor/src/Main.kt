class Point3D {
    var x: Int = 0
    var y: Int = 0
    var z: Int = 0
}

fun createPoint(x: Int, y: Int, z: Int): Point3D {
    val point3d = Point3D()
    point3d.x = x
    point3d.y = y
    point3d.z = z
    return point3d
}
