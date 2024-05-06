using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace a2
{
    public partial class Form1 : Form
      
    {private SqlConnection connection;
        private SqlDataAdapter dataAdapterParent;
        private SqlDataAdapter dataAdapterChild;
        private DataSet dataSet;
        private List<TextBox> textBoxes;
        public Form1()
        {
            this.connection = new SqlConnection("Data Source = DESKTOP-R01UH5Q\\SQLEXPRESS; Initial Catalog = MakeUpShop; Integrated Security = True; TrustServerCertificate=True;");
            this.dataAdapterParent = new SqlDataAdapter();
            this.dataAdapterChild = new SqlDataAdapter();
            this.dataSet = new DataSet();
            this.textBoxes = new List<TextBox>();
            InitializeComponent();
            parentTableLoad();
            loadTextBoxex();

            this.childTable.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
        }
        private void parentTableLoad()
        {
            this.parentTable.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            string select = ConfigurationSettings.AppSettings["selectParent"];
            string parentTableName = ConfigurationSettings.AppSettings["parentTable"];
            dataAdapterParent.SelectCommand = new SqlCommand(select, connection);
            this.dataSet.Clear();
            this.dataAdapterParent.Fill(dataSet, parentTableName);
            this.parentTable.DataSource = dataSet.Tables[parentTableName];
        }

        private void loadTextBoxex()
        {
            try
            {
                List<string> columnNames = new List<string>(ConfigurationSettings.AppSettings["columnNames"].Split(','));
                int pointX = 30;
                int pointY = 40;
                this.panel1.Controls.Clear();
                foreach(string column in columnNames)
                {

                    Label label = new Label();
                    label.Text = column;
                    label.Location = new Point(pointX, pointY);
                    label.Width = 100; 
                    this.panel1.Controls.Add(label);
                   
                    TextBox textbox = new TextBox();
                    
                    textbox.Text = column;
                    textbox.Name = column;
                    textBoxes.Add(textbox);
                    textbox.Location = new Point(pointX + 120, pointY);
                    textbox.Visible = true;
                    textbox.Parent = this.panel1;
                    textbox.Width = 160;
                    panel1.Show();
                    pointY += 50;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            try
            {
                
                string insert = ConfigurationSettings.AppSettings["addQuery"];
                dataAdapterChild.InsertCommand = new SqlCommand(insert, connection);

                string childTableName = ConfigurationSettings.AppSettings["childTable"];
                List<string> columnNames = new List<string>(ConfigurationSettings.AppSettings["columnNames"].Split(','));

                // we go throguh all these columnNames
                // and then we parse the list of textBoxes in order to find the one whose name is the same as the columnName 
                foreach (string columnName in columnNames)
                {
                    foreach (TextBox tb in textBoxes)
                    {
                        if (tb.Name.Equals(columnName) && tb.Name != "id")
                        {
                            dataAdapterChild.InsertCommand.Parameters.AddWithValue("@" + columnName, tb.Text);
                        }
                    }
                }

                // we execute the insert command
                connection.Open();
                dataAdapterChild.InsertCommand.ExecuteNonQuery();
                MessageBox.Show("Inserted successfully in the Database!", "", MessageBoxButtons.OK, MessageBoxIcon.Information);
                connection.Close();

                // we update the child table
                dataSet = new DataSet();
                dataAdapterChild.Fill(dataSet, childTableName);
                childTable.DataSource = this.dataSet.Tables[childTableName];

                this.clearTextBoxes();
            }
            catch (Exception ex)
            {

                MessageBox.Show(ex.Message, "", MessageBoxButtons.OK, MessageBoxIcon.Error);
                connection.Close();
            }
        }



        private void clearTextBoxes()
        {
            foreach (TextBox tb in textBoxes)
            {
                tb.Clear();
            }
        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            DialogResult dialogResult;
            dialogResult = MessageBox.Show("Are you sure?", "Please confirm deletion", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

            if (dialogResult.Equals(DialogResult.Yes))
            {
                try
                {
                    string delete = ConfigurationSettings.AppSettings["deleteQuery"];


                    dataAdapterChild.DeleteCommand = new SqlCommand(delete);
                    dataAdapterChild.DeleteCommand.Connection = connection;

                    string childTableName = ConfigurationSettings.AppSettings["childTable"];
                    List<string> columnNames = new List<string>(ConfigurationSettings.AppSettings["columnNames"].Split(','));
                    string primaryKey = ConfigurationSettings.AppSettings["primaryKey"];

                    foreach (TextBox tb in textBoxes)
                    {
                        if (tb.Name == primaryKey)
                        {
                            this.dataAdapterChild.DeleteCommand.Parameters.AddWithValue("@" + primaryKey, tb.Text);
                        }
                        break;
                    }

                    this.connection.Open();
                    dataAdapterChild.DeleteCommand.ExecuteNonQuery();
                    MessageBox.Show("Successfully deleted from Database!", "", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    this.connection.Close();

                    this.dataSet = new DataSet();
                    this.dataAdapterChild.Fill(dataSet, childTableName);
                    childTable.DataSource = dataSet.Tables[childTableName];

                    this.clearTextBoxes();
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, "", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    this.connection.Close();
                }
            }
        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            try
            {
                // take the update command from app settings
                string update = ConfigurationSettings.AppSettings["updateQuery"];

                // create the update cmd and add its parameters
                dataAdapterChild.UpdateCommand = new SqlCommand(update, connection);

                string childTableName = ConfigurationSettings.AppSettings["childTable"];
                List<string> columnNames = new List<string>(ConfigurationSettings.AppSettings["columnNames"].Split(','));

                foreach (string columnName in columnNames)
                {
                    foreach (TextBox tb in textBoxes)
                    {
                        if (tb.Name.Equals(columnName))
                        {
                            this.dataAdapterChild.UpdateCommand.Parameters.AddWithValue("@" + columnName, tb.Text);
                        }
                    }
                }


                // open connection and execute the command
                this.connection.Open();
                dataAdapterChild.UpdateCommand.ExecuteNonQuery();
                MessageBox.Show("Updated succesfull!", "", MessageBoxButtons.OK, MessageBoxIcon.Information);
                this.connection.Close();

                // repopulate child table
                this.dataSet = new DataSet();
                this.dataAdapterChild.Fill(dataSet, childTableName);
                childTable.DataSource = dataSet.Tables[childTableName];
                this.clearTextBoxes();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "", MessageBoxButtons.OK, MessageBoxIcon.Error);
                this.connection.Close();
            }
        }

        private void parentTable_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            clearTextBoxes(); // we clear the text boxes

            // take the current selected row
            DataGridViewRow selectedParent = parentTable.SelectedRows[0];

            // we take the name of the foreign key
            string foreignKey = ConfigurationSettings.AppSettings["foreignKey"];

            string selectChild = ConfigurationSettings.AppSettings["selectChild"];
            string childTableName = ConfigurationSettings.AppSettings["childTable"];
            List<string> columnNames = new List<string>(ConfigurationSettings.AppSettings["columnNames"].Split(','));


            if (selectedParent.Cells[0].Value.ToString() != String.Empty)
            {
                // We go through our textBoxes and look for the one with the name as the foreign key, and we populate it with the value of the foreign key

                foreach (TextBox tb in textBoxes)
                {
                    if (tb.Name == foreignKey)
                    {
                        tb.Text = selectedParent.Cells[0].Value.ToString();
                    }
                }

                if (this.parentTable.SelectedRows.Count > 0)
                {
                    // we take the id of the library 
                    string id = selectedParent.Cells[0].Value.ToString();

                    // create a new sql command with the productTypeId parameter
                    dataAdapterChild.SelectCommand = new SqlCommand(selectChild + id, connection);

                    // create a new data set and repopulate the child table
                    dataSet = new DataSet();
                    this.dataAdapterChild.Fill(dataSet, childTableName);
                    this.childTable.DataSource = dataSet.Tables[childTableName];


                }
            }

        }

        private void childTable_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            int index = childTable.SelectedRows[0].Index;


            string childTableName = ConfigurationSettings.AppSettings["childTable"];

            try
            {
                for (int i = 0; i < textBoxes.Count; i++)
                {
                    this.textBoxes[i].Text = dataSet.Tables[childTableName].Rows[index][i].ToString();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "", MessageBoxButtons.OK, MessageBoxIcon.Error);

            }

        }
    }
}
