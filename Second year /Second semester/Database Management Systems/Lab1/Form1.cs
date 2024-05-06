using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Data.Odbc;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace a1
{
    public partial class Form1 : Form
    {
        private SqlConnection dbConnection;
        private SqlDataAdapter adapter;
        private DataSet dataSet;
        public Form1()
        {
            InitializeComponent();

        }
        private void Form1_Load(object sender, EventArgs e)
        {
            dbConnection = new SqlConnection("Data Source = DESKTOP-R01UH5Q\\SQLEXPRESS; Initial Catalog = MakeUpShop; Integrated Security = True; TrustServerCertificate=True;");
            adapter = new SqlDataAdapter();
            dataSet = new DataSet();

            parentTable.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            parentTableLoad();

        }

        private void parentTableLoad()
        {
            adapter.SelectCommand = new SqlCommand("select * from Employees", dbConnection);
            dataSet.Clear();
            adapter.Fill(dataSet, "Employees");
            parentTable.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            parentTable.DataSource = dataSet.Tables["Employees"];



        }

        private void childTableLoad()
        {
            dataSet = new DataSet();
            adapter.Fill(dataSet, "EmployeeAttendance");
            childTable.DataSource = dataSet.Tables["EmployeeAttendance"];
            childTable.SelectionMode= DataGridViewSelectionMode.FullRowSelect;

        }

        private void clearTextBoxes()
        {
            attendanceIDTextBox.Clear();
            employeeIDTextBox.Clear();
            workedHoursTextBox.Clear();
        }






        private void addButton_Click(object sender, EventArgs e)
        {
            try
            {
                adapter.InsertCommand = new SqlCommand("insert into EmployeeAttendance(AttendanceID, EmployeeID, WorkedHours) values (@aid, @eid, @hours)", dbConnection);
  
                adapter.InsertCommand.Parameters.Add("@aid", SqlDbType.Int).Value = Int32.Parse(attendanceIDTextBox.Text);
                adapter.InsertCommand.Parameters.Add("@eid", SqlDbType.Int).Value = Int32.Parse(employeeIDTextBox.Text);

                adapter.InsertCommand.Parameters.Add("@hours", SqlDbType.Int).Value = Int32.Parse(workedHoursTextBox.Text);

                dbConnection.Open();
                adapter.InsertCommand.ExecuteNonQuery();
                MessageBox.Show("Inserted successfully in the database");
                dbConnection.Close();

                parentTableLoad();
                clearTextBoxes();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "", MessageBoxButtons.OK, MessageBoxIcon.Error);
                dbConnection.Close();
            }


        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            try
            {
                int index = childTable.SelectedRows[0].Index;
                DialogResult dialogResult;
                dialogResult = MessageBox.Show("Are you sure?", "Please confirm the deletion", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

                if (dialogResult == DialogResult.Yes)
                {
                    adapter.DeleteCommand = new SqlCommand("delete from EmployeeAttendance where AttendanceID = @id", dbConnection);
                    adapter.DeleteCommand.Parameters.Add("@id", SqlDbType.Int).Value = Int32.Parse(attendanceIDTextBox.Text);
                    dbConnection.Open();
                    adapter.DeleteCommand.ExecuteNonQuery();
                    MessageBox.Show("Successfully deleted from database", "", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    dbConnection.Close();

                    childTableLoad();
                    clearTextBoxes();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "", MessageBoxButtons.OK, MessageBoxIcon.Error);
                dbConnection.Close();
            }


        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            try
            {
                int index = childTable.SelectedRows[0].Index;
                adapter.UpdateCommand = new SqlCommand("update EmployeeAttendance set EmployeeID = @eid, WorkedHours = @hours where AttendanceID = @aid", dbConnection);
                adapter.UpdateCommand.Parameters.Add("@aid", SqlDbType.Int).Value = Int32.Parse(attendanceIDTextBox.Text);
                adapter.UpdateCommand.Parameters.Add("@hours", SqlDbType.Int).Value = Int32.Parse(workedHoursTextBox.Text);
                adapter.UpdateCommand.Parameters.Add("@eid", SqlDbType.Int).Value = Int32.Parse(employeeIDTextBox.Text);

                dbConnection.Open();
                adapter.UpdateCommand.ExecuteNonQuery();
                MessageBox.Show("Updated succesfull", "", MessageBoxButtons.OK, MessageBoxIcon.Information);
                dbConnection.Close();

                childTableLoad();
                clearTextBoxes();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "", MessageBoxButtons.OK, MessageBoxIcon.Error);
                dbConnection.Close();
            }
        }

        private void parentTable_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            DataGridViewRow selected = parentTable.SelectedRows[0];
            if (selected.Cells[0].Value.ToString() != String.Empty)
            {
                employeeIDTextBox.Text = selected.Cells[0].Value.ToString();

                int employeeID = Convert.ToInt32(selected.Cells[0].Value);
                adapter.SelectCommand = new SqlCommand("select * from EmployeeAttendance where EmployeeID = @eid", dbConnection);
                adapter.SelectCommand.Parameters.AddWithValue("@eid", employeeID);

                childTableLoad();


            }
        }

        private void childTable_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (childTable.SelectedRows.Count > 0)
            {
                int index = childTable.SelectedRows[0].Index;

                if (index < dataSet.Tables["EmployeeAttendance"].Rows.Count)
                {
                    attendanceIDTextBox.Text = dataSet.Tables["EmployeeAttendance"].Rows[index][0].ToString();
                    employeeIDTextBox.Text = dataSet.Tables["EmployeeAttendance"].Rows[index][1].ToString();
                    workedHoursTextBox.Text = dataSet.Tables["EmployeeAttendance"].Rows[index][2].ToString();
                }
            }
        }
    }
}

