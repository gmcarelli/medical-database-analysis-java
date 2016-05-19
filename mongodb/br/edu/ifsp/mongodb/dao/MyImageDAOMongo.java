package br.edu.ifsp.mongodb.dao;

import java.util.List;

import org.bson.Document;
import org.bson.types.Binary;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.edu.ifsp.dao.IDAO;
import br.edu.ifsp.dao.ImageFileDAO;
import br.edu.ifsp.model.MyImage;

public class MyImageDAOMongo extends ImageFileDAO implements IDAO<MyImage> {

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> mongoCollection;

	public MyImageDAOMongo() {

		this.mongoClient = new MongoClient();

		this.mongoDatabase = mongoClient.getDatabase("MedicalDatabaseAnalysis");

		this.mongoCollection = mongoDatabase.getCollection("MyImage");

	}

	@Override
	public boolean insert(MyImage myImage) {

		boolean writeResult = false;

		if (this.mongoClient != null) {

			Document document = new Document().append("imageId", myImage.getImageId())
					.append("imageName", myImage.getImageName()).append("imageBytes",new Binary(myImage.getImageBytes()));

			try {
				
				this.mongoCollection.insertOne(document);
				
				writeResult = true;
				
			} catch (Exception e) {
				
				writeResult = false;
				
			}					

		}

		this.mongoClient.close();

		return writeResult;

	}

	@Override
	public boolean update(MyImage object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(MyImage object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MyImage search(int imageId) {		

		MyImage myImage = null;	

		if (this.mongoClient != null) {
			
			/**
			 * FIXME
			 */
			DBCollection dbCollection = mongoClient.getDB("MedicalDatabaseAnalysis").getCollection("MyImage");
			
			DBCursor dbCursor = dbCollection.find(new BasicDBObject("imageId", imageId));
			
			//dbCursor = this.mongoCollection.find();

			if (dbCursor.hasNext()) {			

				DBObject object = dbCursor.next();				

				myImage = new MyImage();

				myImage.setImageId((int) object.get("imageId"));

				myImage.setImageName((String) object.get("imageName"));
				
				byte[] imageBytes = (byte[]) object.get("imageBytes");

				myImage.setImageBytes((imageBytes));

			}

		}

		return myImage;
	}

	@Override
	public List<MyImage> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
