import assert from "node:assert";
import { describe, it } from "node:test";

import { forDate, forMonthAndDay } from "./index.js";

/**
 * Tests for the forMonthAndDay function
 */
describe("forMonthAndDay", () => {
  it("should return correct prayer times for a given month and day", async () => {
    const result = await forMonthAndDay("colombo", 9, 17);
    assert.deepEqual(result, {
      fajr: { hour: 4, minute: 45 },
      sunrise: { hour: 6, minute: 1 },
      dhuhr: { hour: 12, minute: 7 },
      asr: { hour: 15, minute: 14 },
      asr_hanafi: { hour: 16, minute: 24 },
      maghrib: { hour: 18, minute: 11 },
      isha: { hour: 19, minute: 21 },
    });
  });
});

/**
 * Tests for the forDate function
 */
describe("forDate", () => {
  it("should return correct prayer times for a given date", async () => {
    const result = await forDate("colombo", new Date("2023-09-17"));
    assert.deepEqual(result, {
      fajr: { hour: 4, minute: 45 },
      sunrise: { hour: 6, minute: 1 },
      dhuhr: { hour: 12, minute: 7 },
      asr: { hour: 15, minute: 14 },
      asr_hanafi: { hour: 16, minute: 24 },
      maghrib: { hour: 18, minute: 11 },
      isha: { hour: 19, minute: 21 },
    });
  });
});
