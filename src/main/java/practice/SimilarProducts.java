package practice;

/**
 * @author Santosh Manughala (SM030146).
 */
public class SimilarProducts {
//    Implement a function to return the N highest rated products that are considered similar to a given product. Each product has an id, a rating, and a list of products it is similar to. Each of those similar products has an id, a rating, and its own list of similar products. -> amazon
//
//
//    A -> B
//    B -> C
//
//
//// map <long, List<Product>>
//
//
//    input: 3
//    Product A {
//        A1
//        4
//        {
//            Product B {
//            B1
//            5
//            {
//                Product C{
//                C1
//                6
//                {}
//            }
//            }
//            Product D {
//                D1
//                3
//                {
//                    Product C..{}
//                }
//            }
//        }
//        }
//
//        ouput:
//        C, B, A
//
//        A-> b
//        B-> a
//
//        Product {
//            long id;
//            int rating;
//            List<Product> similarProducts;
//        }
//
//        main(Product product, int n) {
//            List<Product> outputList = Lists.newArrayList();
//            outputList = findSimilars(outputMap, product);
//            Collections.sort(outputList, Comparator<rating>);
//            List<Product> returnList = Lists.newArrayListWithExpectedSize(n);
//            returnList.add(add only n values from outputlist);
//            return returnList;
//        }
//
//        private Map<Long, List<Product>> findSimilars(List<Product> outputList, List<Product> products) {
//            for(Product similarProduct : products) {
//                outputList.add(similarProduct.getSimilarProducts());
//                for(Product product : similarProduct.getSimilarProducts().getSimilarProducts()) {
//                    findSimilars(outputList, similarProduct.getSimilarProducts)
//                }
//            }
//
//            return outputList;
//        }
}
