using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace Button_grid
{
    /// <summary>
    /// Interakční logika pro MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private Frame frame;

        private Settings settings = new Settings();
        private GameLogic logic;
        private List<Button> buttons = new List<Button>();
        private Button shown_button1;
        private int shown_pair1;
        private Button shown_button2;
        private int shown_pair2;
        private int shown_buttons;
        private List<Button> solvedbtns = new List<Button>();
        private bool freeze;
        public MainWindow()
        {
            InitializeComponent();
            frame = new Frame();
            logic = new GameLogic(settings.Get_height(), settings.Get_width());



            CreateButtonGrid(settings.Get_width(), settings.Get_height());
        }
        private void SwitchScreens()
        {
            GameOver g = new GameOver();
            g.Show();
        }
        private bool IsGameOver()
        {
            if(solvedbtns.Count == logic.Get_btncount())
            {
                return true;
            }
            return false;
        }
        public void Reset()
        {
            solvedbtns.Clear();
            foreach(Button btn in buttons)
            {
                btn.Content = "";
            }
        }
        private void CreateButtonGrid(int width, int height)
        {
            Grid buttonGrid = new Grid();
            buttonGrid.ShowGridLines = true;
            for (int row = 0; row < height; row++)
            {
                buttonGrid.RowDefinitions.Add(new RowDefinition());
            }
            for (int column = 0; column < width; column++)
            {
                buttonGrid.ColumnDefinitions.Add(new ColumnDefinition());
            }
            for (int row = 0; row < height; row++)
            {
                for (int column = 0; column < width; column++)
                {
                    Button button = new Button();
                    //button.Content = "Button " + (row * width + column + 1);
                    button.Name = "btn" + (row * width + column + 1).ToString();
                    button.Click += Card_selected;
                    Grid.SetRow(button, row);
                    Grid.SetColumn(button, column);
                    buttonGrid.Children.Add(button);
                    buttons.Add(button);
                }
            }
            Grid.SetRow(buttonGrid, 1);
            Grid.SetColumn(buttonGrid, 1);
            mainFrame.Children.Add(buttonGrid);
        }
        private void Card_selected(object sender, RoutedEventArgs e)
        {
            

            Console.WriteLine(shown_buttons);
            Button button = (Button)sender;
            if (!(solvedbtns.Contains(button)))
            {
                int index = int.Parse((button.Name).Replace("btn", string.Empty)) - 1;
                int pair = logic.Get_pair(index);
                if (!freeze)
                {
                    Console.WriteLine("Ready!");
                    if (shown_buttons == 0)
                    {
                        shown_pair1 = pair;
                        shown_button1 = button;
                        Show_Content(shown_button1, shown_pair1);
                        shown_buttons++;
                    }
                    else
                    {
                        shown_pair2 = pair;
                        shown_button2 = button;
                        Show_Content(shown_button2, shown_pair2);

                        if (shown_pair1 != shown_pair2)
                        {
                            freeze = true;

                            Hide_Content(shown_button1);
                            Hide_Content(shown_button2);
                            
                        }
                        else
                        {
                            solvedbtns.Add(shown_button1);
                            solvedbtns.Add(shown_button2);
                            shown_buttons = 0;
                        }

                    }
                }
            }
            if (IsGameOver())
            {
                SwitchScreens();
            }

        }


    
    private void Show_Content(Button btn, int pair)
    {

        btn.Content = pair.ToString();
    }
    private async void Hide_Content(Button btn)
    {
        await Task.Delay(1000);
        btn.Content = "";
        shown_buttons = 0;
        freeze = false;

        }
}
    }
