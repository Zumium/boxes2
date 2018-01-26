namespace java cn.zumium.boxes.thrift

typedef string Timestamp

struct Box {
    1: i64 id,
    2: string name,
    3: string description,
    4: Timestamp createdAt,
}

exception BoxServiceException {
    1: string op,
    2: string why,
}

service BoxService {
    void create(1:string name, 2:string description) throws (1:BoxServiceException excp),
    list<Box> currentBoxes() throws (1:BoxServiceException excp),
}