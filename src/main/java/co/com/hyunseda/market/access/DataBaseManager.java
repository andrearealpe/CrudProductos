package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.service.ProductService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author earea
 */
public class DataBaseManager implements IDataBaseManager{
    
    private Connection conn;
    
    public DataBaseManager() {
        initDatabase();
    }
    
    private void initDatabase() {
        // SQL statement for creating a new table
        String sqlProducts = "CREATE TABLE IF NOT EXISTS products (\n"
                + "	productId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	name text NOT NULL,\n"
                + "	description text NULL\n"
                + ");";
        // SQL para crear la tabla de categorías
        String sqlCategories = "CREATE TABLE IF NOT EXISTS categories (\n"
                + " categoryId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sqlProducts);
            stmt.execute(sqlCategories);
            //this.disconnect();
            //System.out.println("Table created or already exists.");

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println("Error creating table: " + ex.getMessage());
        }
    }

    public void connect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
        // Si ya está conectada, nada
        return;
    }
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows
        //String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            
            // Llama a initDatabase después de conectar
            initDatabase();  // Crear la tabla si no existe

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    // CRUD para productos
    @Override
    public boolean save(Product newProduct) {
        PreparedStatement pstmt = null;

        try {
            this.connect(); 
            
            String sql = newProduct.getCategory() != null
                    ? "INSERT INTO products (name, description, categoryId) VALUES (?, ?, ?)"
                    : "INSERT INTO products (name, description) VALUES (?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newProduct.getName());
            pstmt.setString(2, newProduct.getDescription());

            // Si tiene categoría, asignarla al PreparedStatement
            if (newProduct.getCategory() != null) {
                pstmt.setLong(3, newProduct.getCategory().getCategoryId());
            }

            int rowsAffected = pstmt.executeUpdate();  // Ejecuta el insert y verifica las filas afectadas
            if (rowsAffected > 0) {
                return true;  // Si al menos una fila fue afectada, el guardado fue exitoso
            } else {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.WARNING, "No se afectaron filas al intentar guardar el producto.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, "Error al guardar el producto: " + ex.getMessage(), ex);
        } finally {
            // Cerrar el PreparedStatement para evitar fugas
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, "Error al cerrar PreparedStatement: " + ex.getMessage(), ex);
                }
            }
            this.disconnect();  // Asegurarse de desconectar al final del proceso
        }

        return false;  // En caso de que falle el guardado
    }

   
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getLong("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                products.add(product);
                //System.out.println("Number of products found: " + products.size());  
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Error finding products: " + ex.getMessage());
        } finally {
            this.disconnect();
        }
        return products;
    }
    
    @Override
    public List<Product> findByCategory(Long categoryId) {
        List<Product> products = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM products WHERE categoryId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, categoryId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getLong("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));

                // Recuperar la category del producto (si es necesario)
                Category category = new Category();
                category.setCategoryId(rs.getLong("categoryId"));
                product.setCategory(category);

                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return products;
    }

    
    @Override
    public Product findById(Long id) {
        Product product = null;
        try {
            this.connect();
            String sql = "SELECT * FROM products WHERE productId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getLong("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                //System.out.println("Product with ID " + id + " not found.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Error in findById: " + ex.getMessage());
        } finally {
            this.disconnect();
        }
        return product;
    }
    
    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM products WHERE name LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getLong("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return products;
    }

    @Override
    public boolean edit(Long id, Product product, Category category) {
        try {
            this.connect();
            String sql = "UPDATE products SET name = ?, description = ?, category = ? WHERE productId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setString(3, product.getCategory().getName());
            pstmt.setLong(4, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.connect();
            String sql = "DELETE FROM products WHERE productId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return false;
    }

    // CRUD para CATEGORÍAS
    
    @Override
    public boolean saveCategory(Category category) {
        try {
            this.connect();
            String sql = "INSERT INTO categories (name) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return false;
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM categories";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getLong("categoryId"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return categories;
    }

    @Override
    public Category findCategoryById(Long id) {
        Category category = null;
        try {
            this.connect();
            String sql = "SELECT * FROM categories WHERE categoryId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getLong("categoryId"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return category;
    }

    @Override
    public boolean editCategory(Long id, Category category) {
        try {
            this.connect();
            String sql = "UPDATE categories SET name = ? WHERE categoryId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Long id) {
        try {
            this.connect();
            String sql = "DELETE FROM categories WHERE categoryId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return false;
    }

}
