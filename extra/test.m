gap = 1:100;

par1 = 16;
exp = 0;
lv = 0;

exp = exp + par1 / gap(lv+1);

while(1)
    exp = (exp - 1) * gap(lv+1);
    lv = lv + 1;
    exp = exp / gap(lv+1);
    if(exp < 1.0)
        break;
    end
end

lv