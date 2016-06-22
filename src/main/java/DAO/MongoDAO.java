package DAO;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;

import java.net.UnknownHostException;
import java.util.Set;
/**
 * Created by Ruslan Zhdan on 22.06.2016.
 */

public class MongoDAO extends Mongo {

    private Mongo connection = null;
    private DB db = null;

    private static MongoDAO mongoDao = null;

    private MongoDAO() throws UnknownHostException{
    //Connection is created as below
        connection= new Mongo("localhost" ,27017);
        db = connection.getDB("MyDB");
    }
    // A singleton design pattern to get the connection for the outside world
    public static MongoDAO getInstance() throws UnknownHostException{
        if(mongoDao==null){
            mongoDao = new MongoDAO();
        }
        return mongoDao;
    }

    //This is how you create a table in the mongoDB
    public MongoCollection createNewCollection(String tableName)throws Exception{
        //If tableName doesn’t exist create it
        Set tableNames = db.getCollectionNames();
        if(!tableNames.contains(tableName)){
            DBObject dbobject = new BasicDBObject();
            db.createCollection(tableName, dbobject);
        }
        return null;
    }

    /*
    This is how you insert a record in the database
    Every record is a DBObject which is a map type where a key is the column name and value is column value
    */
    public void saveToDB(String collectionName, BasicDBObject dbObject)throws Exception{
        DBCollection dbCollection = db.getCollection(collectionName);
        dbCollection.insert(dbObject);
    }

    //This is how you can retrieve all the table names
    public Set getCollectionNames()throws Exception{
        return db.getCollectionNames();
    }

    //This is how you can retrieve all the rows in the table
    public void showDB(String collectionName)throws Exception{
        DBCollection dbCollection = db.getCollection(collectionName);
        DBCursor cur = dbCollection.find();
        while(cur.hasNext()) {
            System.out.println(cur.next());
        }
    }

    public DBCursor getAllRows(String collectionName)throws Exception{
        DBCollection dbCollection = db.getCollection(collectionName);
        DBCursor cur = dbCollection.find();
        return cur;
    }

    //Below code shows how you could get the row count
    public int getRowCount(String collectionName)throws Exception{
        DBCollection dbCollection = db.getCollection(collectionName);
        DBCursor cur = dbCollection.find();
        return cur.count();
    }

    //Below code shows how to filter the data using where clause. A where clause is a BasicDBObject type with key as the filter column name and the value is the filter value.
    public DBCursor findByColumn(String collectionName, DBObject whereClause)throws Exception{
        DBCursor result = null;
        DBCollection dbCollection = db.getCollection(collectionName);
        result = dbCollection.find(whereClause);
        return result;
    }

    public DBCollection getCollectionByName(String collectionName)throws Exception{
        return db.getCollection(collectionName);
    }

    public void createIndex(String collectionName, String columnName) throws Exception{
        DBCollection dbCollection = db.getCollection(collectionName);
        DBObject indexData = new BasicDBObject(columnName,1);
        dbCollection.createIndex(indexData);
    }

    public void dropTable(String collectionName){
        db.getCollection(collectionName).drop();
    }
}
