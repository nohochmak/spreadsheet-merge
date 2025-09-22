package org.yaxche;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;



@Path("/spreadsheet")
public class SpreadsheetResource {

    @Inject
    SpreadsheetService spreadsheetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<List<String>> getSpreadsheetData() throws IOException {
        // Replace with the actual path to your Excel file
        String filePath = "src/main/resources/MemberListFull_2025Sep18.xlsx"; 
        return spreadsheetService.readExcelFile(filePath);
    }
}