namespace a1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.parentTable = new System.Windows.Forms.DataGridView();
            this.childTable = new System.Windows.Forms.DataGridView();
            this.attendanceID = new System.Windows.Forms.Label();
            this.employeeID = new System.Windows.Forms.Label();
            this.workedHours = new System.Windows.Forms.Label();
            this.attendanceIDTextBox = new System.Windows.Forms.TextBox();
            this.employeeIDTextBox = new System.Windows.Forms.TextBox();
            this.workedHoursTextBox = new System.Windows.Forms.TextBox();
            this.addButton = new System.Windows.Forms.Button();
            this.deleteButton = new System.Windows.Forms.Button();
            this.updateButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.parentTable)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.childTable)).BeginInit();
            this.SuspendLayout();
            // 
            // parentTable
            // 
            this.parentTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parentTable.Location = new System.Drawing.Point(13, 23);
            this.parentTable.Name = "parentTable";
            this.parentTable.Size = new System.Drawing.Size(450, 184);
            this.parentTable.TabIndex = 0;
            this.parentTable.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.parentTable_CellClick);
            // 
            // childTable
            // 
            this.childTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childTable.Location = new System.Drawing.Point(497, 23);
            this.childTable.Name = "childTable";
            this.childTable.Size = new System.Drawing.Size(440, 184);
            this.childTable.TabIndex = 1;
            this.childTable.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.childTable_CellClick);
            // 
            // attendanceID
            // 
            this.attendanceID.AutoSize = true;
            this.attendanceID.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.attendanceID.Location = new System.Drawing.Point(13, 246);
            this.attendanceID.Name = "attendanceID";
            this.attendanceID.Size = new System.Drawing.Size(156, 29);
            this.attendanceID.TabIndex = 2;
            this.attendanceID.Text = "AttendanceID";
            // 
            // employeeID
            // 
            this.employeeID.AutoSize = true;
            this.employeeID.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.employeeID.Location = new System.Drawing.Point(13, 306);
            this.employeeID.Name = "employeeID";
            this.employeeID.Size = new System.Drawing.Size(145, 29);
            this.employeeID.TabIndex = 3;
            this.employeeID.Text = "EmployeeID";
            // 
            // workedHours
            // 
            this.workedHours.AutoSize = true;
            this.workedHours.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.workedHours.Location = new System.Drawing.Point(13, 362);
            this.workedHours.Name = "workedHours";
            this.workedHours.Size = new System.Drawing.Size(161, 29);
            this.workedHours.TabIndex = 4;
            this.workedHours.Text = "WorkedHours";
            // 
            // attendanceIDTextBox
            // 
            this.attendanceIDTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.attendanceIDTextBox.Location = new System.Drawing.Point(200, 246);
            this.attendanceIDTextBox.Name = "attendanceIDTextBox";
            this.attendanceIDTextBox.Size = new System.Drawing.Size(263, 31);
            this.attendanceIDTextBox.TabIndex = 5;
            // 
            // employeeIDTextBox
            // 
            this.employeeIDTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.employeeIDTextBox.Location = new System.Drawing.Point(200, 306);
            this.employeeIDTextBox.Name = "employeeIDTextBox";
            this.employeeIDTextBox.Size = new System.Drawing.Size(263, 31);
            this.employeeIDTextBox.TabIndex = 6;
            // 
            // workedHoursTextBox
            // 
            this.workedHoursTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.workedHoursTextBox.Location = new System.Drawing.Point(200, 362);
            this.workedHoursTextBox.Name = "workedHoursTextBox";
            this.workedHoursTextBox.Size = new System.Drawing.Size(263, 31);
            this.workedHoursTextBox.TabIndex = 7;
            // 
            // addButton
            // 
            this.addButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.addButton.Location = new System.Drawing.Point(660, 221);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(132, 45);
            this.addButton.TabIndex = 8;
            this.addButton.Text = "ADD";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // deleteButton
            // 
            this.deleteButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.deleteButton.Location = new System.Drawing.Point(660, 286);
            this.deleteButton.Name = "deleteButton";
            this.deleteButton.Size = new System.Drawing.Size(132, 49);
            this.deleteButton.TabIndex = 9;
            this.deleteButton.Text = "DELETE";
            this.deleteButton.UseVisualStyleBackColor = true;
            this.deleteButton.Click += new System.EventHandler(this.deleteButton_Click);
            // 
            // updateButton
            // 
            this.updateButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.updateButton.Location = new System.Drawing.Point(660, 352);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(132, 48);
            this.updateButton.TabIndex = 10;
            this.updateButton.Text = "UPDATE";
            this.updateButton.UseVisualStyleBackColor = true;
            this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(949, 430);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.deleteButton);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.workedHoursTextBox);
            this.Controls.Add(this.employeeIDTextBox);
            this.Controls.Add(this.attendanceIDTextBox);
            this.Controls.Add(this.workedHours);
            this.Controls.Add(this.employeeID);
            this.Controls.Add(this.attendanceID);
            this.Controls.Add(this.childTable);
            this.Controls.Add(this.parentTable);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.parentTable)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.childTable)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView parentTable;
        private System.Windows.Forms.DataGridView childTable;
        private System.Windows.Forms.Label attendanceID;
        private System.Windows.Forms.Label employeeID;
        private System.Windows.Forms.Label workedHours;
        private System.Windows.Forms.TextBox attendanceIDTextBox;
        private System.Windows.Forms.TextBox employeeIDTextBox;
        private System.Windows.Forms.TextBox workedHoursTextBox;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button deleteButton;
        private System.Windows.Forms.Button updateButton;
    }
}

