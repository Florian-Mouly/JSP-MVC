
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Florian MOULY
 */


public class DAO {
        
    protected final DataSource myDataSource;

    /**
     *
     * @param dataSource la source de données à utiliser
     */
    public DAO(DataSource dataSource) {
            this.myDataSource = dataSource;
    }

    public String addDiscountCode(String DiscountCode, float rate) throws SQLException, Exception {
            int result = 0;
            String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";
            try (Connection connection = myDataSource.getConnection(); 
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, DiscountCode);
                    stmt.setFloat(2, rate);
                    result = stmt.executeUpdate();
            }
            
            if (result != 1) // On n'a pas modifié la table
            {
                throw new Exception("Les valeurs" + DiscountCode + " " + rate + " n'ont pas été insérées.");
            }
            
            return "Les valeurs" + DiscountCode + " " + rate + "ont été insérées.";
    }

    /**
     * Supprime un enregistrement dans la table DISCOUNT_CODE
     * @param code la clé de l'enregistrement à supprimer
     * @return le nombre d'enregistrements supprimés (1 ou 0)
     * @throws java.sql.SQLException renvoyées par JDBC
     **/
    public String deleteDiscountCode(String code) throws SQLException, Exception {
            int result = 0;
            String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
            try (Connection connection = myDataSource.getConnection(); 
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, code);
                    result = stmt.executeUpdate();
            }
            
            if (result != 1) // On n'a pas modifié la table
            {
                throw new Exception("La ligne associée au code " + code + " n'a pas été modifiée.");
            }
            
            return "La ligne associée au code " + code + " a été supprimée.";
    }
    
    /**
     * Retourne tous les codes disponibles dans la table DISCOUNT_CODE
     * @return
     * @throws SQLException 
     */ 
    Map<String, Float> allCodes() throws SQLException {
            Map<String, Float> result = new HashMap<>();

            String sql = "SELECT * FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
            try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, "DISCOUNT_CODE");
                    try (ResultSet rs = stmt.executeQuery()) {
                            while (rs.next()) {
                                    String id = rs.getString("DISCOUNT_CODE");
                                    float rate = rs.getFloat("RATE");

                                    result.put(id, rate);
                            }
                    }
            }

            return result;
    }
}