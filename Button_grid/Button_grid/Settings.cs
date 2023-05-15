using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Button_grid
{
    class Settings
    {
        private int paircount = 6;
        private int width;
        private int height;
        

        public Settings()
        {
            Calculate_dimensions();
        }
        private void Calculate_dimensions()
        {
            int target = paircount * 2;
            int a = (int)Math.Floor(Math.Sqrt(target));
            int b = (int)Math.Floor(Math.Sqrt(target));
            while(a * b != target)
            {
                if(a * b < target)
                {
                    a++;
                }
                else
                {
                    b--;
                }
            }
            width = a;
            height = b;
            
        }
        public int Get_width()
        {
            return width; 
        }
        public int Get_height()
        {
            return height;
        }
    }

}
