package application;

public class Program {

    public static void main(String[] args){
        
        DatabaseService databaseService = new DatabaseService();

        //databaseService.ReadData();
        //databaseService.InsertData();
        //databaseService.UpdateData();
        databaseService.DeleteData();

        //This method print the generated key of the inserted row.
        //databaseService.InsertDataWithGeneratedKeys();
    }
}
