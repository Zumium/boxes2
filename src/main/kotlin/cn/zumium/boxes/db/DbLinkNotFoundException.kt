package cn.zumium.boxes.db

class DbLinkNotFoundException(val id: Long) : Exception("link with $id not found")
