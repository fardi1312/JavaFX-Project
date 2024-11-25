package se.jobsesprit.services;
import se.jobsesprit.entities.UserEntreprise;
import se.jobsesprit.interfaces.InterfaceCRUD;
import se.jobsesprit.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import se.jobsesprit.entities.UserEtudiant;

public class UserEntrepriseCRUD implements InterfaceCRUD<UserEntreprise> {

    @Override
    public void ajouterEntities(UserEntreprise userEntreprise) {
        try {
            String requete = "INSERT INTO UserEntreprise(phone, role, email, motDePasse, nomEntreprise, localisation, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, userEntreprise.getPhone());
            pst.setString(2, userEntreprise.getRole());
            pst.setString(3, userEntreprise.getEmail());
            pst.setString(4, userEntreprise.getMotDePasse());
            pst.setString(5, userEntreprise.getNomEntreprise());
            pst.setString(6, userEntreprise.getLocalisation());
            pst.setString(7, userEntreprise.getImage());

            pst.executeUpdate();
            System.out.println("Utilisateur d'entreprise ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<UserEntreprise> listeDesEntities() {
        List<UserEntreprise> myList = new ArrayList<>();
        try {
            String requete = "SELECT phone, role, email, motDePasse, nomEntreprise, localisation, image FROM UserEntreprise";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                UserEntreprise userEntreprise = new UserEntreprise();
                userEntreprise.setPhone(rs.getInt("phone"));
                userEntreprise.setRole(rs.getString("role"));
                userEntreprise.setEmail(rs.getString("email"));
                userEntreprise.setMotDePasse(rs.getString("motDePasse"));
                userEntreprise.setNomEntreprise(rs.getString("nomEntreprise"));
                userEntreprise.setLocalisation(rs.getString("localisation"));
                userEntreprise.setImage(rs.getString("image"));

                myList.add(userEntreprise);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void modifierEntities(UserEntreprise userEntreprise) {
        try {
            String requete = "UPDATE UserEntreprise SET phone = ?, email = ?, motDePasse = ?, nomEntreprise = ?, localisation = ?, image = ? WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, userEntreprise.getPhone());
            pst.setString(2, userEntreprise.getEmail());
            pst.setString(3, userEntreprise.getMotDePasse());
            pst.setString(4, userEntreprise.getNomEntreprise());
            pst.setString(5, userEntreprise.getLocalisation());
            pst.setString(6, userEntreprise.getImage());
            pst.setInt(7, userEntreprise.getId());

            pst.executeUpdate();
            System.out.println("Utilisateur d'entreprise modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(UserEntreprise userEntreprise) {
        try {
            String requete = "DELETE FROM UserEntreprise WHERE id = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, userEntreprise.getId());

            pst.executeUpdate();
            System.out.println("Utilisateur d'entreprise supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public UserEntreprise findByMail(String mail) throws SQLException{
        UserEntreprise u =new UserEntreprise();
        String req="SELECT * FROM `userentreprise` WHERE `email`=?";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement((req));
        st.setString(1,mail);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
                u.setId(rs.getInt("id"));
                u.setLocalisation(rs.getString("localisation"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getInt("phone"));
                u.setNomEntreprise(rs.getString("nomEntreprise"));
                u.setMotDePasse(rs.getString("motDePasse"));
                u.setImage(rs.getString("image"));
                u.setRole(rs.getString("role"));
        }

        return u;

    }
    
     public UserEntreprise authenticate(String mail, String password) throws SQLException{
        UserEntreprise u = null;
       

            String sql = "SELECT * FROM UserEntreprise WHERE `email` = ? AND `motDePasse` = ?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(sql);
            ps.setString(1, mail);
            ps.setString(2, password);
        
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u=new UserEntreprise();


                u.setId(rs.getInt("id"));
                u.setNomEntreprise(rs.getString("NomEntreprise"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getInt("phone"));
                u.setMotDePasse(rs.getString("motDePasse"));
                u.setImage(rs.getString("image"));
                u.setLocalisation(rs.getString("Localisation"));
                u.setRole("ENTREPRISE");
                
                
            }

        return u;
    }
}
