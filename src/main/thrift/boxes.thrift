namespace java cn.zumium.boxes.thrift

typedef string Timestamp

struct Box {
    1: i64 id,
    2: string name,
    3: string description,
    4: BoxStatus status,
    5: Timestamp createdAt,
}

enum BoxStatus {
    OPEN,
    ARCHIVED
}

enum AddBy {
    COPY,
    MOVE
}

enum FetchBy {
    COPY,
    MOVE
}

enum LsType {
    FILE,
    DIR
}

struct LsItem {
    1:string name,
    2:LsType type,
}

enum LinkType {
    SOFT,
    HARD
}

struct Link {
    1:i64 boxId,
    2:string innerPath,
    3:string destination,
    4:LinkType type,
}

exception ServiceException {
    1: string op,
    2: string why,
}

service BoxService {
    //Create
    void create(1:string name, 2:string description) throws (1:ServiceException excp),

    //Delete
    void remove(1:i64 id) throws (1:ServiceException excp),

    //Update
    void setDescription(1:i64 id, 2:string description) throws (1:ServiceException excp),
    void setName(1:i64 id, 2:string name) throws (1:ServiceException excp),

    //Archive & Unarchive
    void archive(1:i64 id) throws (1:ServiceException excp),
    void unarchive(1:i64 id) throws (1:ServiceException excp),

    //Read
    list<Box> currentBoxes() throws (1:ServiceException excp),
    Box get(1:i64 id) throws (1:ServiceException excp),
}

service FileService {
    //Add
    void add(1:i64 boxId, 2:string innerPath 3:string outerPath, 4: AddBy addBy) throws (1:ServiceException excp),

    //Move Out
    void fetch(1:i64 boxId, 2:string innerPath, 3:string outerPath, 4:FetchBy fetchBy) throws (1:ServiceException excp),

    //Remove
    void remove(1:i64 boxId, 2:string innerPath) throws (1:ServiceException excp),

    //Read
    list<LsItem> ls(1:i64 boxId, 2:string innerDir) throws (1:ServiceException excp),

    //Move & Copy Among Boxes
    void move(1:i64 srcBoxId, 2:string srcInnerPath, 3:i64 dstBoxId, 4:string dstInnerPath) throws (1:ServiceException excp),
    void copy(1:i64 srcBoxId, 2:string srcInnerPath, 3:i64 dstBoxId, 4:string dstInnerPath) throws (1:ServiceException excp),
    void innerMove(1:i64 boxId, 2:string srcInnerPath, 3:string dstInnerPath) throws (1:ServiceException excp),
    void innerCopy(1:i64 boxId, 2:string srcInnerPath, 3:string dstInnerPath) throws (1:ServiceException excp),
}

service LinkService {
    //Create
    void create(1:i64 boxId, 2:string innerPath, 3:string destination, 4:LinkType linkType) throws (1:ServiceException excp),

    //Read
    list<Link> lsAll() throws (1:ServiceException excp),
    list<Link> lsBox(1:i64 boxId) throws (1:ServiceException excp),
    list<Link> lsInner(1:i64 boxId, 2:string innerPath) throws (1:ServiceException excp),

    //Delete
    void removeAll() throws (1:ServiceException excp),
    void removeById(1:i64 id) throws (1:ServiceException excp),
    void removeByBox(1:i64 boxId) throws (1:ServiceException excp),
    void removeByInner(1:i64 boxId, 2:string innerPath) throws (1:ServiceException excp),
    void removeByDestination(1:string destination) throws (1:ServiceException excp),
}