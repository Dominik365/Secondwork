using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Button_grid
{
    class GameLogic
    {
        private List<int> pairs;
        public GameLogic(int height, int width)
        {
            pairs = new List<int>();
            for (int i = 0; i < height * width; i++)
            {
                pairs.Add(i % ((height * width) / 2));
            }
            Random rnd = new Random();
            for (int i = 0; i < height * width; i++)
            {
                int randomIndex = rnd.Next(i, height * width);
                int temp = pairs.ElementAt(i);
                pairs[i] = pairs.ElementAt(randomIndex);
                pairs[randomIndex] = temp;
            }
        }
        public int Get_pair(int index)
        {
            return pairs.ElementAt(index);    
        }
        public int Get_btncount()
        {
            return pairs.Count;
        }
    }
}
