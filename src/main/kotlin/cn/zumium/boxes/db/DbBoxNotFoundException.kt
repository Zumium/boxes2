package cn.zumium.boxes.db

class DbBoxNotFoundException(val id: Long) : Exception("box with id $id not found")
