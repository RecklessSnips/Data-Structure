package Tree.BST;

import java.util.Random;

public class HeroTree {
    /*
    相关方法的解析如果实在想不起来，可参考https://www.youtube.com/watch?v=GtflM7nUrU0
    时间: 26:55
     */

    // 这个是🌲的最大深度！
    private int depth;
    /*
    实现 BST 的插入操作，分别实现用 while loop 和 用 recursion
     */
    Hero root;

    /*
     只用 while。需要先判断是否存在一个root，如果没有，则将新node添加为 root

     用 while，可以延续 linkedList 的思路，如果可以加，就加，否则跳到下一个元素
     否则要决定添加到 left 还是 right
     如果是 左边，需要考虑是否直接添加，还是要添加到左边的左边，还是左边的右边，以此类推；右边同理
     若左边 child 是空，则直接添加 -> current.left = node
                       else    -> current = current.left
     */

    public Hero getRoot(){
        return root;
    }

    public void add(Hero hero){
        // 添加 root
        if (root == null){
            root = hero;
            return;
        }
        Hero current = root;
        while (true){
            // 左边
            if (hero.getId() < current.getId()){
                // 找到合适的位置，就添加
                if (current.getLeft() == null){
                    // 需要重新设置 left 或者 right
                    current.setLeft(hero);
                    return;
                }else{
                    // 否则一直一直沿着 left sub tree找，下一次 current会作为 parent开始，因为要一直更新current
                    current = current.getLeft();
                }
            }else{
                // 右边
                if (current.getRight() == null){
                    current.setRight(hero);
                    return;
                }else{
                    current = current.getRight();
                }
            }
        }
    }

    public void addRecur(Hero hero){
        root = addRecurHelper(hero, getRoot());
    }

    /*
     用递归
     （Base Case）：如果当前节点为空，意味着已找到插入新节点的位置，因此创建一个新节点。这将在上一层的递归中设置为父节点的左或右子节点。
     注意，递归的时候要注意返回值，如果遇到null则添加，但是如何一直拿着所插入的hero和不断在变化的node来比较呢？
     所以要一直更新current，也就是说我们的方法要一直将不断更新的current的值不断传给里面的 递归 方法。
     最后将设置好的 node 返回给上一层，让整个 递归 能联系上，相当于每添加一个node，
     所有被遍历过的node都要重新调用一下setLeft 或者 setRight
     */
    public Hero addRecurHelper(Hero hero, Hero current){
        /*
        hero代表要插入的node，而 current 用来帮助递归整个🌲
         */
        // base case，如果需要current为null，说明要在这里加入，并将结果返回给上一层，为了维护链接
        if (current == null){
            return hero;
        }
        // recursion 开始根据情况递归
        if (hero.getId() < current.getId()){
            /*
             从左边开始递归，为了维护整个tree的链接，需要重新设置 left 或者 right, 因为里层的递归会返回最终的node
             并且更新current的值，还要维持原本的hero不变，完成递归
             */
            current.setLeft(addRecurHelper(hero, current.getLeft()));
        }else{
            current.setRight(addRecurHelper(hero, current.getRight()));
        }

        /*
         这里返回current的原因是，返回给上一层的递归方法时候，调用的方法是 setLeft或者setRight，而为了
         保证链接不断，就是把当前的node返回给上一层，就相当于重新从最新添加的node开始，反着，重新设置了一遍 left和right ！
         */
        return current;
    }

    // 根据id删除一个英雄，但是至于是取左边最大还是右边最小，先完成一个：获取从某一个节点开始的最大深度() 的函数
    public Hero delete(int id){
        /*
        从root开始找，建立两个关于root的reference：parent和current，parent用来以后删除node，
        current用来遍历tree。直到current的id等于要删除id.
         */
        Hero parent = null;
        Hero current = root;
        Hero deleted = null;
        // 判断parent该删除哪边，左边为true，右边为false
        boolean isLeft = false;
        // current ！= null 检查当前node是否已经到达leaf，current.getId() != id 检查是否找到所删除目标
        while (current != null && current.getId() != id){
            // 保存当前node为parent
            parent = current;
            if (id < current.getId()){
                // 从左边开始查找
                // 说明想要删除当前current的左边
                isLeft = true;
                current = current.getLeft();
            }else {
                // 从右边开始查找
                // 说明想要删除当前current的右边
                isLeft = false;
                current = current.getRight();
            }
        }
        // 退出循环，说明要么没找到要删除的英雄，返回null，否则找到了，开始考虑3种情况
        if (current == null){
            System.out.println("未找到所要删除的英雄");
            return null;
        }
        /*
         情况1: 如果current为leaf; 情况2: current只有一个child，不管哪个情况，用被删除的node的下一个
         来当作替代，因为最终只会有一个node被parent链接，要么是current的left或者right，或者就是null
         */
        if (current.getLeft() == null || current.getRight() == null) {
            Hero replacement = (current.getLeft() != null) ? current.getLeft() : current.getRight();
            if (parent == null) {
                root = replacement; // 删除的是根节点，因为parent未被更新过
            } else if (isLeft) {
                parent.setLeft(replacement);
            } else {
                parent.setRight(replacement);
            }
            return current; // 返回被删除的节点
        }

        /*
        情况3:
        current左右都有node，那么为了保证🌲最低深度，通过分别判断左右的最高深度，用（depthFromNode()）
        来判断用左边最大的node，还是右边最小的node来替换current的位置，选择深度最大的, 为了减少整体🌲的深度！
        然后将这个点替换成current即可
         */
        int leftDepth = depthFromNode(current.getLeft());
        int rightDepth = depthFromNode(current.getRight());
        // 判断往去哪边来找替换的node
        boolean onLeft;
        System.out.println(leftDepth);
        System.out.println(rightDepth);
        deleted = current;
        // 用来从当前current开始往下寻找替换的node
        Hero subCurrent = current;
        // 一会用来删除要替换的node, 设置成subCurrent是因为可能删除的node就在下一层
        Hero subParent = subCurrent;
        if (leftDepth > rightDepth){
            // 找左边的最大值: 最右的node
            onLeft = true;
            subCurrent = subCurrent.getLeft();
            while (subCurrent.getRight() != null){
                subParent = subCurrent;
                subCurrent = subCurrent.getRight();
            }
        }else{
            // 找右边的最小值: 最左的node
            onLeft = false;
            subCurrent = subCurrent.getRight();
            while (subCurrent.getLeft() != null){
                subParent = subCurrent;
                subCurrent = subCurrent.getLeft();
            }
        }
        // 退出循环，说明此时的subCurrent就是要被替换的node， 且要么在最左边，要么最右边
        System.out.println("被删除的英雄的ID: " + current.getId());
        // 没删除root，parent都会存在
        if (parent != null) System.out.println("被删除的Parent的ID: " + parent.getId());
        System.out.println("所替换Hero的ID: " + subCurrent.getId());
        System.out.println("所替换Hero的Parent ID: " + subParent.getId());

        /*
         如果删除的是root，那么parent会是null，
         current，subParent会是root本身，subCurrent会是替代的
         */
        if (parent == null){
            /*
            但是要分两种情况，如果被替换的node的parent直接是root本身，那么说明这个node只有
             一个right/left child，需要把当前node的sub tree传给root，也就是自己。
             可参考图片中，如果删除8，则会选择10上去当node，那么14要被直接链接到root的右边

             如果node的parent不是当前被删除的root，那么需要把自己的sub tree给到当前parent
             比如只看图片左半部分，以3为root的sub tree，如果删除3，那么会把4放到root，但
             这个时候只需要把4的右边给到subParent
             */

            if (current == subParent){
                // 判断往去哪边来找替换的node
                currentIsParent(subCurrent, current, onLeft);
            }else{
                currentIsNOTParent(subCurrent, subParent, current, onLeft);
            }
            // 不管哪种情况，将最终的subCurrent替换为root
            root = subCurrent;
            return deleted;
        }

        // 如果这个node有左侧或者右侧subtree，需要用此时的subParent连起来，确保不丢失sub tree
        if (current == subParent){
            currentIsParent(subCurrent, current, onLeft);
        }else{
            currentIsNOTParent(subCurrent, subParent, current, onLeft);
        }

        /*
         就剩下将所替换的subCurrent放到原来的被删除node（current）上，
         将current所有的left，right给subCurrent
         将current的parent的左或者右，设置为subCurrent，即可
        */
        if (isLeft){
            // 将当前parent的链接给到所替换的subCurrent
            parent.setLeft(subCurrent);
        }else{
            parent.setRight(subCurrent);
        }
        return deleted;
    }

    public void currentIsParent(Hero replacement, Hero parent, boolean onLeft){
        // 找左边最大，那么左边要保留，右边要设置为当前被删除的node的右边
        if (onLeft){
            /*
             可以参考图片的10，如果是往左边找，可以确定右边一定是要被重新替换
             所以要手动设置右边，变成当前root的右边。而左边需要保持自身本来的sub tree
             所以不碰左边！！！
            */
            replacement.setRight(parent.getRight());
        }else{
            /*
            同理，参考图片的10，因为10是从右边找的替换node，那么它的左边必须被换成当前
            root的左边，而右边要保持原来的样子，所以不变
            */
            replacement.setLeft(parent.getLeft());
        }
    }

    public void currentIsNOTParent(Hero replacement, Hero parent, Hero current, boolean onLeft){
        if (onLeft){
            /*
            因为在左边找最大值，说明找的是最右边的node，现在这个node没了，就要把
            subParent的右边设置为这个node的左边（因为他自己在最右边，不可能再有右边了）
             */
            parent.setRight(replacement.getLeft());
        }else{
            /*
             如果往右边找的最小值，说明我们的subCurrent在这个sub tree的最左边,
             subParent的right要保留其原有值，防止丢失sub Tree
             将左边设置为被替代的node的右边
             */
            parent.setLeft(replacement.getRight());
        }
        /*
        这一步将所替换的node的左右变成当前被删除的（current）左右，完成替换
         */
        replacement.setLeft(current.getLeft());
        replacement.setRight(current.getRight());
    }

    // 判断是否为leaf node，需满足左右node同时为null
    public boolean isLeaf(Hero hero){
        return hero.getLeft() == null && hero.getRight() == null;
    }

    // 来判断一个node是否只有一个child
    public boolean hasOneChild(Hero hero){
        // 检查是否仅有左子节点存在
        if (hero.getLeft() != null && hero.getRight() == null) {
            return true;
        }
        // 检查是否仅有右子节点存在
        else if (hero.getLeft() == null && hero.getRight() != null) {
            return true;
        }
        // 如果两个条件都不满足，则表示节点或者是叶节点，或者有两个子节点
        return false;
    }

    /**
     *  从这个点开始的 sub tree 的最大深度（从root到leaf的节点数为深度）
     *     5
     *   /   \
     *  3     6      这个数深度为 2
     * @param hero: 从这个点开始
     * @return int: 返回最大深度
     */
    public int depthFromNode(Hero hero){
        /*
        比如我们从hero开始，左边开始递归，得到深度，右边也递归得到深度，不断递归
        在function返回的时候（也就是左右递归完毕，回归到这个node），二者比较一个max，就得到了这个点开始的最大深度,
        不断递归，回归...

        Base case:
        何时停止？在树里，通常都是把我们的指针移到null值，也就是左或者右 child的位置上，才开始判断是否当前这个
        位置为null，如果是null，那么忽略这一层的高度，返回0，否则每一次递归，都要 +1
         */
        if (hero == null) return 0;
        // 获取左边subtree的高度
        int leftDepth = depthFromNode(hero.getLeft());
        // 获取右边subtree的高度
        int rightDepth = depthFromNode(hero.getRight());
        // 注意，必须要加1因为是为了将当前node和左或者右 subtree的高度链接起来，所以必须加 1
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int getDepth(){
        return depthFromNode(root);
    }

    public void inOrder(Hero node){
        if (node == null) return;
        inOrder(node.getLeft());
        System.out.println(node);
        inOrder(node.getRight());
    }

    /*
     根据 id 查找一个英雄是否在这个 tree 里，
     用递归和普通循环分别实现
     {可以记录一共查询了多少次(这个需要加一个class variable，不能通过方法传参来实现}
     */
    public boolean searchHeroRecur(int id, Hero current){
        /*
         base case
         如果到了 leaf node的某一侧，为null，说明该英雄不存在
         */
        if (current == null){
            return false;
        }else if (id == current.getId()){
            return true;
        }
        /*
        recursion body
         */
        boolean ifFind = false;
        if (id < current.getId()){
            // 往左边查找
            ifFind = searchHeroRecur(id, current.getLeft());
        }else{
            // 往右边查找
            ifFind = searchHeroRecur(id, current.getRight());
        }
        return ifFind;
    }

    public static void main(String[] args) {
        HeroTree tree = new HeroTree();
        // 打出 1 2 3 4
//        tree.add(new Hero(1, "天魁星", "及时雨", "宋江"));
//        tree.add(new Hero(2, "天罡星", "玉麒麟", "卢俊义"));
//        tree.add(new Hero(3, "天机星", "智多星", "吴用"));
//        tree.add(new Hero(4, "天闲星", "入云龙", "公孙胜"));
//        tree.inOrder(tree.getRoot());

        // 打出 1 3 4 6 7 8 10 13 14
//        tree.add(new Hero(8, "天威星", "双鞭", "呼延灼"));
//        tree.add(new Hero(3, "天机星", "智多星", "吴用"));
//        tree.add(new Hero(10, "天贵星", "小旋风", "柴进"));
//        tree.add(new Hero(1, "天魁星", "及时雨", "宋江"));
//        tree.add(new Hero(6, "天雄星", "豹子头", "林冲"));
//        tree.add(new Hero(14, "天伤星", "天伤星", "武松"));
//        tree.add(new Hero(4, "天闲星", "入云龙", "公孙胜"));
//        tree.add(new Hero(7, "天猛星", "霹雳火", "秦明"));
//        tree.add(new Hero(13, "天孤星", "花和尚", "鲁智深"));
//        tree.inOrder(tree.getRoot());

        // 打出 1 3 4 6 7 8 10 13 14 图片见本package HeroTree1.png
//        tree.addRecur(new Hero(8, "天威星", "双鞭", "呼延灼"));
//        tree.addRecur(new Hero(3, "天机星", "智多星", "吴用"));
//        tree.addRecur(new Hero(10, "天贵星", "小旋风", "柴进"));
//        tree.addRecur(new Hero(1, "天魁星", "及时雨", "宋江"));
//        tree.addRecur(new Hero(6, "天雄星", "豹子头", "林冲"));
//        tree.addRecur(new Hero(14, "天伤星", "天伤星", "武松"));
//        tree.addRecur(new Hero(4, "天闲星", "入云龙", "公孙胜"));
//        tree.addRecur(new Hero(7, "天猛星", "霹雳火", "秦明"));
//        tree.addRecur(new Hero(13, "天孤星", "花和尚", "鲁智深"));
//        tree.inOrder(tree.getRoot());

        // 以下 9 个删除，是根据scratch一步步对照着删除的
//        int id1 = 3;
//        tree.delete(id1);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id1, tree.searchHeroRecur(id1, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//
//        int id2 = 7;
//        tree.delete(id2);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id2, tree.searchHeroRecur(id2, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//
//        int id3 = 13;
//        tree.delete(id3);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id3, tree.searchHeroRecur(id3, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//
//        int id4 = 8;
//        tree.delete(id4);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id4, tree.searchHeroRecur(id4, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//
//        int id5 = 4;
//        tree.delete(id5);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id5, tree.searchHeroRecur(id5, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//
//        int id6 = 1;
//        tree.delete(id6);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id6, tree.searchHeroRecur(id6, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//
//        int id7 = 10;
//        tree.delete(id7);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id7, tree.searchHeroRecur(id7, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//
//        int id8 = 14;
//        tree.delete(id8);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id8, tree.searchHeroRecur(id8, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//
//        int id9 = 6;
//        tree.delete(id9);
//        tree.inOrder(tree.getRoot());
//        System.out.printf("是否存在英雄 %d: %b\n", id9, tree.searchHeroRecur(id9, tree.getRoot()));
//        System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());

        // 这块是Scratch后面的只删除 root
//        tree.addRecur(new Hero(8, "天威星", "双鞭", "呼延灼"));
//        tree.addRecur(new Hero(3, "天机星", "智多星", "吴用"));
//        tree.addRecur(new Hero(10, "天贵星", "小旋风", "柴进"));
//        tree.addRecur(new Hero(1, "天魁星", "及时雨", "宋江"));
//        tree.addRecur(new Hero(6, "天雄星", "豹子头", "林冲"));
//        tree.addRecur(new Hero(14, "天伤星", "天伤星", "武松"));
//        tree.addRecur(new Hero(4, "天闲星", "入云龙", "公孙胜"));
//        tree.addRecur(new Hero(7, "天猛星", "霹雳火", "秦明"));
//        tree.addRecur(new Hero(13, "天孤星", "花和尚", "鲁智深"));
//        tree.inOrder(tree.getRoot());
//
//        int counter = 0;
//        long startTime = System.currentTimeMillis();
//        while (tree.getRoot() != null){
//            counter++;
//            int id = tree.getRoot().getId();
//            tree.delete(id);
//            tree.inOrder(tree.getRoot());
//            System.out.printf("是否存在英雄 %d: %b\n", id, tree.searchHeroRecur(id, tree.getRoot()));
//            System.out.printf("🌲的最大高度为: %d\n\n", tree.getDepth());
//        }
//        System.out.println(counter);
//        long endTime = System.currentTimeMillis();
//        // 计算并打印执行时间
//        long duration = endTime - startTime;
//        System.out.println("执行时间：" + duration + " 毫秒");

        // 测试binary tree 的添加于查找的性能
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            int id = random.nextInt(100000) + 1;
            tree.addRecur(new Hero(id, "天雄星", "豹子头", "林冲"));
        }
        System.out.printf("是否存在英雄 %d: %b\n", 99990, tree.searchHeroRecur(99990, tree.getRoot()));
        long endTime = System.currentTimeMillis();
        // 计算并打印执行时间
        long duration = endTime - startTime;
        System.out.println("执行时间：" + duration + " 毫秒");

        /*
        1,  天魁星   及时雨   宋江
        2	天罡星	玉麒麟	卢俊义
        3	天机星	智多星   吴用
        4	天闲星	入云龙   公孙胜
        5	天勇星	大刀	    关胜
        6	天雄星	豹子头   林冲
        7	天猛星	霹雳火	秦明
        8	天威星	双鞭	    呼延灼
        9	天英星	小李广   花荣
        10	天贵星	小旋风	柴进
        11	天富星	扑天雕	李应
        12	天满星	美髯公	朱仝
        13	天孤星	花和尚	鲁智深
        14	天伤星	天伤星   武松
        15	天立星	双枪将	董平
        16	天捷星	没羽箭	张清
        17	天暗星	青面兽	杨志
        18	天祐星	金枪手	徐宁
        19	天空星	急先锋	索超
        20	天速星	神行太保	戴宗
        21	天异星	赤髪鬼	刘唐
        22	天杀星	黑旋风   李逵
        23	天微星	九纹龙	史进
        24	天究星	没遮拦	穆弘
        25	天退星	挿翅虎	雷横
        26	天寿星	混江龙	李俊
        27	天剑星	立地太岁	阮小二
        28	天平星	船火儿	张横
        29	天罪星	短命二郎	阮小五
        30	天损星	浪里白条	张顺
        31	天败星	活阎罗	阮小七
        32	天牢星	病关索	杨雄
        33	天慧星	拼命三郎	石秀
        34	天暴星	两头蛇	解珍
        35	天哭星	双尾蝎	解宝
        36	天巧星	燕小乙	燕青
     */
    }
}
