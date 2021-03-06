/*
 * @(#)OutputView.java      0.2 2019.12.21
 *
 * Copyright (c) 2019 lxxjn0
 */

package view;

import domain.table.Table;
import domain.menu.Menu;
import domain.menu.MenuQuantity;

import java.util.HashMap;
import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String COMPLETE_PAYMENT_BOTTOM_LINE = "└ ─ ┘";
    private static final String NON_COMPLETE_PAYMENT_BOTTOM_LINE = "└ \\ ┘";

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printPaymentLine(tables, COMPLETE_PAYMENT_BOTTOM_LINE, NON_COMPLETE_PAYMENT_BOTTOM_LINE, size);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printPaymentLine(final List<Table> tables,
                                         final String paymentLine,
                                         final String nonPaymentLine,
                                         final int count) {
        for (int index = 0; index < count; index++) {
            selectPrintLine(tables, paymentLine, nonPaymentLine, index);
        }
        System.out.println();
    }

    private static void selectPrintLine(final List<Table> tables,
                                        final String paymentLine,
                                        final String nonPaymentLine,
                                        final int index) {
        if (tables.get(index).isPaymentCompleted()) {
            System.out.print(paymentLine);
            return;
        }
        System.out.print(nonPaymentLine);
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public static void printFunctions() {
        System.out.println("## 메인화면");
        System.out.println("1 - 주문등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 프로그램 종료");
        System.out.println();
    }

    public static void printOrderedMenuStatus(HashMap<Menu, MenuQuantity> orderedMenuStatus) {
        System.out.println("## 주문 내역");
        System.out.println("메뉴  수량  금액");
        orderedMenuStatus.forEach(OutputView::printSelectedMenuStatus);
    }

    private static void printSelectedMenuStatus(Menu menu, MenuQuantity menuQuantity) {
        System.out.println(menu.getName() + " " + menuQuantity.getMenuQuantity() + " " + menu.getPrice());
    }

    public static void printFinalOrderAmount(double orderAmount) {
        System.out.println("## 최종 결제할 금액");
        System.out.printf("%.1f원\n", orderAmount);
    }
}
