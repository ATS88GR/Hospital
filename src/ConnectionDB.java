import java.sql.*;


public class ConnectionDB {
    public static Connection con;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException {
        con = null;
        Class.forName("org.sqlite.JDBC");  // Загрузка драйвера
        con = DriverManager.getConnection("jdbc:sqlite:Hospital.s3db"); // Подключение/Создание базы

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException {
        statmt = con.createStatement();
        statmt.execute("CREATE TABLE if not exists 'Departments' (" +
                "'Id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'Building' INT NOT NULL CHECK(Building>0 AND Building<5), " +
                "'Financing' MONEY NOT NULL DEFAULT 0 CHECK (Financing>=0), " +
                "'Name' NVCHAR(100) NOT NULL UNIQUE "+
                ");");
        statmt.execute("CREATE TABLE if not exists 'Diseases' (" +
                "'Id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'Name' NVCHAR(100) NOT NULL UNIQUE, " +
                "'Severity' INT DEFAULT 1, " +
                "CHECK(Severity >0)"+
                ");");
        statmt.execute("CREATE TABLE if not exists 'Doctors' (" +
                "'Id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'Name' NVCHAR NOT NULL UNIQUE, " +
                "'Phone' CHAR(10), " +
                "'Salary' MONEY, " +
                "'Surname' NVARCHAR NOT NULL," +
                "CHECK (Salary>0)"+
                ");");
        statmt.execute("CREATE TABLE if not exists 'Examinations' (" +
                "'Id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'DayOfWeek' INT NOT NULL UNIQUE, " +
                "'Phone' CHAR(10), " +
                "'Salary' MONEY, " +
                "'Surname' NVARCHAR NOT NULL," +
                "CHECK (Salary>0)"+
                ");");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {                  // вставка    в какую таблицу    в какие поля              что вставлять
       /* statmt.execute("INSERT INTO 'citizens'          ('bool', 'name', 'phone') VALUES ('true', 'Petya', 125453); ");
        statmt.execute("INSERT INTO 'citizens'          ('bool', 'name', 'phone') VALUES ('false', 'Vasya', 321789); ");
        statmt.execute("INSERT INTO 'citizens'          ('bool', 'name', 'phone') VALUES ('true', 'Masha', 456123); ");*/

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {
        // resSet = statmt.executeQuery("SELECT phone, name FROM citizens");

        while(resSet.next())
        {
            /*int id = resSet.getInt("id");
            boolean bool = Boolean.parseBoolean(resSet.getString("bool"));*/
            /*int  phone = resSet.getInt("phone");
            String  name = resSet.getString("name");*/

            /*System.out.println( "ID = " + id );
            System.out.println( "bool = " + bool );*/
            /*System.out.println( "name = " + name );
            System.out.println( "phone = " + phone );
            System.out.println();*/
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        con.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

}